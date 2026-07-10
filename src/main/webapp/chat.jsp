<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI Multi-Assistant (Chat & Image)</title>
    <style>
        body { 
            font-family: 'Segoe UI', Arial, sans-serif; 
            margin: 40px; 
            background-color: #f0f2f5; 
        }
        .chat-container { 
            max-width: 650px; 
            margin: 0 auto; 
            background: white; 
            padding: 30px; 
            border-radius: 12px; 
            box-shadow: 0 4px 15px rgba(0,0,0,0.08); 
        }
        h2 { 
            color: #1a1a1a; 
            text-align: center; 
            margin-bottom: 25px; 
        }
        textarea { 
            width: 100%; 
            height: 90px; 
            padding: 12px; 
            box-sizing: border-box; 
            border: 1px solid #ccd0d5; 
            border-radius: 6px; 
            resize: none; 
            font-size: 14px; 
        }
        .toggle-group { 
            margin: 15px 0; 
            display: flex; 
            gap: 20px; 
            align-items: center; 
        }
        .toggle-group label { 
            font-weight: 600; 
            color: #4b4b4b; 
            cursor: pointer; 
        }
        button { 
            background-color: #007bff; 
            color: white; 
            border: none; 
            padding: 11px 24px; 
            border-radius: 6px; 
            cursor: pointer; 
            float: right; 
            font-weight: bold; 
            font-size: 14px; 
            transition: background 0.2s; 
        }
        button:hover { 
            background-color: #0056b3; 
        }
        .clear { 
            clear: both; 
        }
        .box { 
            padding: 18px; 
            margin-top: 25px; 
            border-radius: 6px; 
            font-size: 15px; 
            line-height: 1.5; 
        }
        .response-box { 
            background-color: #e2f0d9; 
            border: 1px solid #b5d99c; 
            color: #2e4d1e; 
            white-space: pre-wrap; 
        }
        .image-box { 
            background-color: #e8f4fd; 
            border: 1px solid #b3d7ff; 
            text-align: center; 
        }
        .image-box img { 
            max-width: 100%; 
            height: auto; 
            border-radius: 6px; 
            margin-top: 10px; 
            box-shadow: 0 2px 8px rgba(0,0,0,0.15); 
        }
        .error-box { 
            background-color: #fce4d6; 
            border: 1px solid #f8cbad; 
            color: #c65911; 
        }

        /* --- Responsive Media Queries --- */
        @media screen and (max-width: 768px) {
            body {
                margin: 20px; /* Reduces outer margin on smaller viewports */
            }
            .chat-container {
                padding: 20px; /* Slightly compact inner space */
            }
            h2 {
                font-size: 22px;
                margin-bottom: 20px;
            }
        }

        @media screen and (max-width: 480px) {
            body {
                margin: 10px;
            }
            .chat-container {
                padding: 15px;
                border-radius: 8px;
            }
            h2 {
                font-size: 18px;
            }
            .toggle-group {
                flex-direction: column; /* Stack options vertically on narrow mobile phones */
                align-items: flex-start;
                gap: 10px;
            }
            button {
                width: 100%; /* Make button full width for easy touch tapping */
                float: none;
            }
            .box {
                padding: 12px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

<div class="chat-container">
    <h2>AI Text & Image Generator Dashboard</h2>
    
    <form action="${pageContext.request.contextPath}/send" method="POST">
        <textarea name="message" placeholder="Type your text query or detail your image creation prompt here..." required>${userMessage}</textarea>
        
        <div class="toggle-group">
            <span><strong>Mode:</strong></span>
            <label>
                <input type="radio" name="type" value="text" ${selectedType == null || selectedType == 'text' ? 'checked' : ''}> Ask Chatbot
            </label>
            <label>
                <input type="radio" name="type" value="image" ${selectedType == 'image' ? 'checked' : ''}> Generate Image
            </label>
        </div>

        <button type="submit">Generate</button>
        <div class="clear"></div>
    </form>

    <% if (request.getAttribute("aiResponse") != null) { %>
        <div class="box response-box">
            <strong>Ollama Response:</strong><br><br><%= request.getAttribute("aiResponse") %>
        </div>
    <% } %>

    <% if (request.getAttribute("imageUrl") != null) { %>
        <div class="box image-box">
            <strong>Generated AI Image:</strong><br>
            <img src="<%= request.getAttribute("imageUrl") %>" alt="AI Generated Graphic Output">
        </div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <div class="box error-box">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>
</div>

</body>
</html>