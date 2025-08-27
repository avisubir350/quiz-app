from flask import Flask, request, jsonify, render_template_string
import json
import sqlite3
from datetime import datetime
import os

app = Flask(__name__)

# Initialize SQLite database for storing processed results
def init_db():
    conn = sqlite3.connect('results.db')
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS processed_results (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            submission_id INTEGER,
            user_name TEXT,
            score INTEGER,
            total_questions INTEGER,
            percentage REAL,
            grade TEXT,
            feedback TEXT,
            processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
    ''')
    conn.commit()
    conn.close()

def calculate_grade(percentage):
    """Calculate letter grade based on percentage"""
    if percentage >= 90:
        return 'A'
    elif percentage >= 80:
        return 'B'
    elif percentage >= 70:
        return 'C'
    elif percentage >= 60:
        return 'D'
    else:
        return 'F'

def generate_feedback(score, total, percentage):
    """Generate personalized feedback"""
    if percentage >= 90:
        return "Excellent work! You have mastered the material."
    elif percentage >= 80:
        return "Great job! You have a solid understanding of the concepts."
    elif percentage >= 70:
        return "Good effort! Review the topics you missed for better understanding."
    elif percentage >= 60:
        return "You're on the right track. Consider studying the material more thoroughly."
    else:
        return "Keep practicing! Review all the topics and try again."

@app.route('/process-result', methods=['POST'])
def process_result():
    """Process quiz result from Java service"""
    try:
        data = request.json
        submission_id = data.get('id')
        user_name = data.get('userName')
        score = data.get('score')
        total_questions = data.get('totalQuestions')
        
        # Calculate percentage and grade
        percentage = (score / total_questions) * 100 if total_questions > 0 else 0
        grade = calculate_grade(percentage)
        feedback = generate_feedback(score, total_questions, percentage)
        
        # Store processed result
        conn = sqlite3.connect('results.db')
        cursor = conn.cursor()
        cursor.execute('''
            INSERT INTO processed_results 
            (submission_id, user_name, score, total_questions, percentage, grade, feedback)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        ''', (submission_id, user_name, score, total_questions, percentage, grade, feedback))
        conn.commit()
        conn.close()
        
        return jsonify({
            'status': 'success',
            'message': 'Result processed successfully'
        })
    
    except Exception as e:
        return jsonify({
            'status': 'error',
            'message': str(e)
        }), 500

@app.route('/results/<int:submission_id>')
def get_result(submission_id):
    """Get processed result by submission ID"""
    try:
        conn = sqlite3.connect('results.db')
        cursor = conn.cursor()
        cursor.execute('''
            SELECT * FROM processed_results WHERE submission_id = ?
        ''', (submission_id,))
        result = cursor.fetchone()
        conn.close()
        
        if result:
            result_data = {
                'id': result[0],
                'submission_id': result[1],
                'user_name': result[2],
                'score': result[3],
                'total_questions': result[4],
                'percentage': result[5],
                'grade': result[6],
                'feedback': result[7],
                'processed_at': result[8]
            }
            
            # Return HTML result page
            return render_template_string(RESULT_TEMPLATE, result=result_data)
        else:
            return jsonify({'error': 'Result not found'}), 404
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@app.route('/api/results/<int:submission_id>')
def get_result_json(submission_id):
    """Get processed result as JSON"""
    try:
        conn = sqlite3.connect('results.db')
        cursor = conn.cursor()
        cursor.execute('''
            SELECT * FROM processed_results WHERE submission_id = ?
        ''', (submission_id,))
        result = cursor.fetchone()
        conn.close()
        
        if result:
            return jsonify({
                'id': result[0],
                'submission_id': result[1],
                'user_name': result[2],
                'score': result[3],
                'total_questions': result[4],
                'percentage': result[5],
                'grade': result[6],
                'feedback': result[7],
                'processed_at': result[8]
            })
        else:
            return jsonify({'error': 'Result not found'}), 404
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500

# HTML template for result page
RESULT_TEMPLATE = '''
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Results</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            margin: 0;
            padding: 20px;
            min-height: 100vh;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            overflow: hidden;
        }
        .header {
            background: linear-gradient(45deg, #4CAF50, #45a049);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .header h1 {
            margin: 0;
            font-size: 2.5em;
        }
        .content {
            padding: 40px;
        }
        .result-card {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 25px;
            margin: 20px 0;
            border-left: 5px solid #4CAF50;
        }
        .score-display {
            text-align: center;
            margin: 30px 0;
        }
        .score-circle {
            display: inline-block;
            width: 120px;
            height: 120px;
            border-radius: 50%;
            background: linear-gradient(45deg, #4CAF50, #45a049);
            color: white;
            line-height: 120px;
            font-size: 2em;
            font-weight: bold;
        }
        .grade {
            font-size: 3em;
            font-weight: bold;
            text-align: center;
            margin: 20px 0;
            color: #4CAF50;
        }
        .feedback {
            background: #e3f2fd;
            border-radius: 10px;
            padding: 20px;
            margin: 20px 0;
            border-left: 5px solid #2196F3;
        }
        .stats {
            display: flex;
            justify-content: space-around;
            margin: 30px 0;
        }
        .stat {
            text-align: center;
        }
        .stat-value {
            font-size: 2em;
            font-weight: bold;
            color: #4CAF50;
        }
        .stat-label {
            color: #666;
            font-size: 0.9em;
        }
        .btn {
            display: inline-block;
            background: linear-gradient(45deg, #4CAF50, #45a049);
            color: white;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 25px;
            margin: 10px;
            transition: transform 0.3s;
        }
        .btn:hover {
            transform: translateY(-2px);
        }
        .actions {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🎉 Quiz Results</h1>
            <p>Congratulations, {{ result.user_name }}!</p>
        </div>
        
        <div class="content">
            <div class="score-display">
                <div class="score-circle">
                    {{ "%.1f"|format(result.percentage) }}%
                </div>
            </div>
            
            <div class="grade">Grade: {{ result.grade }}</div>
            
            <div class="stats">
                <div class="stat">
                    <div class="stat-value">{{ result.score }}</div>
                    <div class="stat-label">Correct</div>
                </div>
                <div class="stat">
                    <div class="stat-value">{{ result.total_questions - result.score }}</div>
                    <div class="stat-label">Incorrect</div>
                </div>
                <div class="stat">
                    <div class="stat-value">{{ result.total_questions }}</div>
                    <div class="stat-label">Total</div>
                </div>
            </div>
            
            <div class="result-card">
                <h3>📊 Performance Summary</h3>
                <p><strong>Score:</strong> {{ result.score }} out of {{ result.total_questions }}</p>
                <p><strong>Percentage:</strong> {{ "%.1f"|format(result.percentage) }}%</p>
                <p><strong>Grade:</strong> {{ result.grade }}</p>
                <p><strong>Completed:</strong> {{ result.processed_at }}</p>
            </div>
            
            <div class="feedback">
                <h3>💡 Feedback</h3>
                <p>{{ result.feedback }}</p>
            </div>
            
            <div class="actions">
                <a href="/" class="btn">Take Another Quiz</a>
                <a href="#" onclick="window.print()" class="btn">Print Results</a>
            </div>
        </div>
    </div>
</body>
</html>
'''

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5000)
