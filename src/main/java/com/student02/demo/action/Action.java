package com.student02.demo.action;

import com.student02.demo.vo.ActionForward;

import jakarta.servlet.http.*;


public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
