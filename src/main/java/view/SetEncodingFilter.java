package view;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

public class SetEncodingFilter implements Filter {
    private String encoding;

    public void setFilterConfig( final FilterConfig filterConfig )
    {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if( encoding==null ) encoding="UTF-8";
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
        setFilterConfig( filterConfig );
    }

    public void doFilter(final ServletRequest   request,
                         final ServletResponse  response,
                         FilterChain      chain )
            throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter( request, response );
    }

    public void destroy() {

    }
}