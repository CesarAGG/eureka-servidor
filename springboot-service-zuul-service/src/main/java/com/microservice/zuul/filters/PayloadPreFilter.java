package com.microservice.zuul.filters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PayloadPreFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(PayloadPreFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        try {
            if (request.getInputStream() != null) {
                String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
                logger.info("Request Payload: {}", requestBody);
            }
        } catch (Exception e) {
            logger.error("Error reading request payload", e);
        }

        return null;
    }

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
