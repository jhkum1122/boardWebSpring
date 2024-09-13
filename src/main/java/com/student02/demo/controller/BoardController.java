package com.student02.demo.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.student02.demo.action.Action;
import com.student02.demo.action.BoardDeleteProAction;
import com.student02.demo.action.BoardDetailAction;
import com.student02.demo.action.BoardListAction;
import com.student02.demo.action.BoardModifyFormAction;
import com.student02.demo.action.BoardModifyProAction;
import com.student02.demo.action.BoardReplyFormAction;
import com.student02.demo.action.BoardReplyProAction;
import com.student02.demo.action.BoardWriteProAction;
import com.student02.demo.svc.BoardWriteProService;
import com.student02.demo.vo.ActionForward;
import com.student02.demo.vo.BoardBean;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/home")
public class BoardController extends HttpServlet 
{

    @GetMapping(value = "/index")
    public String getIndex() {
    	System.out.println("index page : 20240912");
        return "index";
    }
    
    @GetMapping(value = "/qna_board_write")
    public String getHome() {
    	System.out.println("qna_board_write page: 20240912");
        return "qna_board_write";
    }
  
    @Autowired
    private BoardWriteProService boardWriteProService;

    
    
    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	System.out.println("process : 20240912");

    	// 요청 파라미터 처리
        String param = request.getParameter("param");
        
        // 응답 처리
        String responseMessage = "처리된 파라미터: " + param;
        
        // 응답 본문 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseMessage);
        
        
        request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
    	System.out.println("command : 20240912" + command);
    	

		if(command.equals("/home/process/boardWriteForm.bo")){
			forward=new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}else if(command.equals("/home/process/boardWritePro.bo")){
			action  = new BoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardList.bo")){
			action = new BoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardDetail.bo")){
			action = new BoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardReplyForm.bo")){
			action = new BoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardReplyPro.bo")){
			action = new BoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardModifyForm.bo")){
			action = new BoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/home/process/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/home/process/boardDeleteForm.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num",board_num);
			forward=new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		}
		else if(command.equals("/home/process/boardDeletePro.bo")){
			action = new BoardDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
		            // 예외 처리 코드
		            e.printStackTrace();
		        } catch (IOException e) {
		            // IOException 처리
		            e.printStackTrace();
		            throw e; // 예외를 다시 던져서 호출자에게 알림
		        }
			}
			
		}
    }
    
    

    @RequestMapping(value = "/process/boardWriteForm.bo", method = RequestMethod.GET)
    public ModelAndView boardWriteForm() {
        System.out.println("process/boardWriteForm.bo : 20240912");

        // 응답을 위한 ModelAndView 객체 생성
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/board/qna_board_write"); // JSP 또는 Thymeleaf 템플릿의 이름
        
        // 필요 시 모델 데이터 추가
        // modelAndView.addObject("key", "value");

        return modelAndView;
    }
    
    @RequestMapping(value = "/process/boardWritePro.bo", method = RequestMethod.GET)
    public ModelAndView boardWriteProForm(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("process/boardWritePro.bo : 20240913 	GET");

        ModelAndView modelAndView = new ModelAndView();
        ActionForward forward=null;
		Action action=null;
        
        action  = new BoardWriteProAction();
        try {
			forward=action.execute(request, response );
		} catch (Exception e) {
			e.printStackTrace();
		}
        
/*        
        	action  = new BoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
*/

        // 필요 시 모델 데이터 추가
        // modelAndView.addObject("key", "value");
        if (forward != null) {
            if (forward.isRedirect()) {
                modelAndView.setViewName("redirect:" + forward.getPath());
            } else {
                modelAndView.setViewName(forward.getPath());
            }
        } else {
            modelAndView.setViewName("qna_board_write"); // 기본적으로 이동할 뷰 설정
        }

        return modelAndView;
    }
    
    
    @RequestMapping(value = "/process/boardWritePro.bo", method = RequestMethod.POST)
    public ModelAndView handleBoardWriteProForm(HttpServletRequest request, 
    		  @RequestParam("BOARD_NAME") String boardName,
              @RequestParam("BOARD_PASS") String boardPass,
              @RequestParam("BOARD_SUBJECT") String boardSubject,
              @RequestParam("BOARD_CONTENT") String boardContent,
              @RequestParam("BOARD_FILE") MultipartFile boardFile) {
System.out.println("process/boardWritePro.bo POST : 20240913");

        ModelAndView modelAndView = new ModelAndView();
        String realFolder = request.getServletContext().getRealPath("/boardUpload");
        File folder = new File(realFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        System.out.println("File save path: " + folder.getAbsolutePath());

        try {
            // 파일 저장
            String fileName = null;
            if (boardFile != null && !boardFile.isEmpty()) {
                fileName = boardFile.getOriginalFilename();
                File destinationFile = new File(folder, fileName);
                System.out.println("File uploaded before : " + destinationFile.getAbsolutePath());
                System.out.println("File uploaded before : " + realFolder);
                System.out.println("File uploaded before : " + fileName);

//                boardFile.transferTo(new File(realFolder, fileName));
                boardFile.transferTo(destinationFile); // 파일을 지정된 경로에 저장
                
                System.out.println("File uploaded successfully: " + destinationFile.getAbsolutePath());

            }

            // BoardBean 객체 생성 및 설정
            BoardBean boardBean = new BoardBean();
            boardBean.setBOARD_NAME(boardName);
            boardBean.setBOARD_PASS(boardPass);
            boardBean.setBOARD_SUBJECT(boardSubject);
            boardBean.setBOARD_CONTENT(boardContent);
            boardBean.setBOARD_FILE(fileName); // 파일 이름을 BoardBean에 설정

            // 게시글 등록
            boolean isWriteSuccess = false;
            try {
                isWriteSuccess = boardWriteProService.registArticle(boardBean);
            } catch (Exception e) {
                // 서비스에서 발생한 예외 처리
                e.printStackTrace();
                modelAndView.addObject("errorMessage", "게시글 등록 중 오류가 발생했습니다.");
                modelAndView.setViewName("/board/qna_board_write");
                return modelAndView;
            }
            
            if (isWriteSuccess) {
                // 성공 시 boardList 페이지로 리다이렉트
                modelAndView.setViewName("redirect:/home/process/boardList.bo");
            } else {
                // 실패 시 qna_board_write.jsp로 이동
                modelAndView.addObject("errorMessage", "글 작성에 실패했습니다.");
                modelAndView.setViewName("/board/qna_board_write");
            }
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("errorMessage", "파일 업로드 중 오류가 발생했습니다.");
            modelAndView.setViewName("/board/qna_board_write");
        }

        return modelAndView;
    }    
    
    @GetMapping("/process/boardList.bo")
    public ModelAndView showBoardList() {
        ModelAndView modelAndView = new ModelAndView();
        // 게시글 목록을 조회하는 로직 추가
        // List<BoardBean> boardList = boardService.getAllBoards();
        // modelAndView.addObject("boardList", boardList);
        modelAndView.setViewName("/board/qna_board_list"); // boardList.jsp로 이동
        return modelAndView;
    }
        
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/home/process/boardWriteForm.bo")){
			forward=new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}else if(command.equals("/home/process/boardWritePro.bo")){
			action  = new BoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardList.bo")){
			action = new BoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardDetail.bo")){
			action = new BoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardReplyForm.bo")){
			action = new BoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardReplyPro.bo")){
			action = new BoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/home/process/boardModifyForm.bo")){
			action = new BoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/home/process/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/home/process/boardDeleteForm.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num",board_num);
			forward=new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		}
		else if(command.equals("/home/process/boardDeletePro.bo")){
			action = new BoardDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
/*
    @GetMapping
    public Collection<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (!personService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        Person updatedPerson = personService.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (!personService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
*/
}
