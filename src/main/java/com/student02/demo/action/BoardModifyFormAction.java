package com.student02.demo.action;


import com.student02.demo.svc.BoardDetailService;
import com.student02.demo.vo.ActionForward;
import com.student02.demo.vo.BoardBean;

import jakarta.servlet.http.*;

public class BoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			BoardDetailService boardDetailService
			= new BoardDetailService();	
		   	BoardBean article =boardDetailService.getArticle(board_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/board/qna_board_modify.jsp");
	   		return forward;
	   		
	 }
	 
}