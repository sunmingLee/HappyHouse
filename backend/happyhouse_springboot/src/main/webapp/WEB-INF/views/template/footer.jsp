<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="footer1">
        <div class="left">
            <img width="150px" src="data:image/png;base64,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" alt="">
        </div>
        <div class="right">
            <div>
                <h2>Find Us</h2>
            </div>
            <hr><br>
            <div>
                (SSAFY) 서울시 강남구 테헤란로 멀티스퀘어
            </div>
            <div>
                1544-9001
            </div>
            <div>
                admin@ssafy.com
        	</div>
        </div>
    </div>
    <div class="footer2">
        Copyright by SSAFY All rights reserved.
    </div>
</body>
</html>