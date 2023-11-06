package com.microservice.zuul.filters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CompressPostFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(CompressPostFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();

        try {
            String encoding = response.getHeader("Content-Encoding");
            if (encoding == null || !encoding.equals("gzip")) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(baos);
                gzipOutputStream.write(ctx.getResponseDataStream().readAllBytes());
                gzipOutputStream.close();

                byte[] compressedResponse = baos.toByteArray();
                ctx.setResponseDataStream(new ByteArrayInputStream(compressedResponse));
                ctx.addZuulResponseHeader("Content-Encoding", "gzip");
                response.setContentLength(compressedResponse.length);
            }
        } catch (IOException e) {
            log.error("Error compressing response", e);
        }

        return null;
    }
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
