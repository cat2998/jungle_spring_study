package study.board.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import study.board.jwt.JwtUtil;

import java.io.IOException;

public class JwtCheckFilter implements Filter {
    private final JwtUtil jwtUtil = new JwtUtil();
    private static final String[] whitelist = {"/", "/api/user/join", "/api/user/login", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                String token = jwtUtil.resolveToken(httpRequest);
                if (token == null || !jwtUtil.validateToken(token)) {
                    httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    httpResponse.setContentType("application/json");
                    httpResponse.setCharacterEncoding("UTF-8");
                    httpResponse.getWriter().write("{\"error\":\"" + HttpStatus.BAD_REQUEST.toString() + "\", \"data\":null, \"message\":\"토큰이 유효하지 않습니다.\"}");
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}