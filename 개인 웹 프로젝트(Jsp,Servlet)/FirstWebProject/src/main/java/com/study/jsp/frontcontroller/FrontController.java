package com.study.jsp.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.FContentService;
import com.study.jsp.command.FDeleteService;
import com.study.jsp.command.FLikeService;
import com.study.jsp.command.FListService;
import com.study.jsp.command.FModifyService;
import com.study.jsp.command.FModifyViewService;
import com.study.jsp.command.FReplyDelete;
import com.study.jsp.command.FReplyListService;
import com.study.jsp.command.FReplyService;
import com.study.jsp.command.FReplyViewService;
import com.study.jsp.command.FWriteService;
import com.study.jsp.command.FmostReplyService;
import com.study.jsp.command.FmostWriterService;
import com.study.jsp.command.IdCheckService;
import com.study.jsp.command.JoinService;
import com.study.jsp.command.LoginService;
import com.study.jsp.command.ModifyService;
import com.study.jsp.command.Service;
import com.study.jsp.command.SnsLoginService;
import com.study.jsp.command.WithdrawService;
import com.study.jsp.command.admin.BanMemberService;
import com.study.jsp.command.admin.SelectAllMemberService;
import com.study.jsp.command.admin.StopMemberService;
import com.study.jsp.command.admin.blackCheckService;
import com.study.jsp.command.alarm.AContentService;
import com.study.jsp.command.alarm.ADeleteService;
import com.study.jsp.command.alarm.ALikeService;
import com.study.jsp.command.alarm.AListService;
import com.study.jsp.command.alarm.AModifyService;
import com.study.jsp.command.alarm.AModifyViewService;
import com.study.jsp.command.alarm.AReplyDelete;
import com.study.jsp.command.alarm.AReplyListService;
import com.study.jsp.command.alarm.AReplyService;
import com.study.jsp.command.alarm.AReplyViewService;
import com.study.jsp.command.alarm.AWriteService;
import com.study.jsp.command.info.DownloadViewService;
import com.study.jsp.command.info.IContentService;
import com.study.jsp.command.info.IDeleteService;
import com.study.jsp.command.info.ILikeService;
import com.study.jsp.command.info.IListService;
import com.study.jsp.command.info.IModifyService;
import com.study.jsp.command.info.IModifyViewService;
import com.study.jsp.command.info.IReplyDelete;
import com.study.jsp.command.info.IReplyListService;
import com.study.jsp.command.info.IReplyService;
import com.study.jsp.command.info.IReplyViewService;
import com.study.jsp.command.info.IWriteService;
import com.study.jsp.command.info.ImostReplyService;
import com.study.jsp.command.info.ImostWriterService;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FrontController() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		Service service = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		
		HttpSession session = null;
		session = request.getSession();
		
		int curPage = 1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		
		String jsonData= "";
		if(com.equals("/join.do")) {
			service = new JoinService();
			service.execute(request,response);
			printW(request, response);
			return;
		} else if(com.equals("/idCheck.do")) {
			service = new IdCheckService();
			service.execute(request, response);
			printW(request, response);
			return;
		} else if(com.equals("/login.do")) {
			service = new LoginService();
			service.execute(request, response);
			printW(request, response);
			return;
		} else if(com.equals("/logoutOk.do")) {
			logoutOk(request, response);
			
			viewPage="mainView.jsp";
		} else if(com.equals("/modify.do")) {
			service = new ModifyService();
			service.execute(request, response);
			printW(request, response);
			return;
		} else if(com.equals("/withdraw.do")) {
			service = new WithdrawService();
			service.execute(request, response);
			printW(request, response);
			logoutOk(request, response);
			return;
		} else if(com.equals("/write.do")) {
			service = new FWriteService();
			service.execute(request, response);
			printW(request, response);
			return;
		} else if(com.equals("/fwriteView.do")) {
			viewPage = "fwriteView.jsp";
		} else if(com.equals("/loginView.do")) {
			viewPage = "loginView.jsp";
		} else if(com.equals("/joinView.do")) {
			viewPage = "joinView.jsp";
		} else if(com.equals("/modifyView.do")) {
			viewPage = "modifyView.jsp";
		} else if(com.equals("/mainView.do")) {
			viewPage = "mainView.jsp";
		} else if(com.equals("/withdrawView.do")) {
			viewPage = "withdrawView.jsp";
		} else if(com.equals("/fListView.do")) {
			service = new FListService();
			service.execute(request, response);
			viewPage = "fListView.jsp";
		}else if(com.equals("/fContentView.do")) {
			service = new FContentService();
			service.execute(request, response);
			service = new FReplyViewService();
			service.execute(request, response);
			service = new FReplyListService();
			service.execute(request, response);
			viewPage = "fContentView.jsp";
		}else if(com.equals("/fDelete.do")) {
			service = new FDeleteService();
			service.execute(request, response);
			viewPage = "fListView.do?page="+curPage;
		}else if(com.equals("/fModifyView.do")){
			service = new FModifyViewService();
			service.execute(request, response);
			viewPage = "fModifyView.jsp";
		}else if (com.equals("/fModify.do")){
			service = new FModifyService();
			service.execute(request, response);
			service = new FContentService();
			service.execute(request, response);
			return;                                         
		}else if(com.equals("/reply.do")) {
			service = new FReplyService();
			service.execute(request, response);
			return;
		}else if(com.equals("/like.do")) {
			service = new FLikeService();
			service.execute(request, response);
			return;
		}else if(com.equals("/freplydelete.do")) {
			service = new FReplyDelete();
			service.execute(request, response);
			return;											//여기부터 공지사항
		}else if(com.equals("/awrite.do")) {
			service = new AWriteService();
			service.execute(request, response);
			printW(request, response);
			return;
		}else if(com.equals("/awriteView.do")) {
			viewPage = "awriteView.jsp";
		}//가즈아
		else if(com.equals("/aListView.do")) {
			service = new AListService();
			service.execute(request, response);
			viewPage = "aListView.jsp";
		}else if(com.equals("/aContentView.do")) {
			service = new AContentService();
			service.execute(request, response);
			service = new AReplyViewService();
			service.execute(request, response);
			service = new AReplyListService();
			service.execute(request, response);
			viewPage = "aContentView.jsp";
		}else if(com.equals("/aDelete.do")) {
			service = new ADeleteService();
			service.execute(request, response);
			viewPage = "aListView.do?page="+curPage;
		}else if(com.equals("/aModifyView.do")){
			service = new AModifyViewService();
			service.execute(request, response);
			viewPage = "aModifyView.jsp";
		}else if (com.equals("/aModify.do")){
			service = new AModifyService();
			service.execute(request, response);
			service = new AContentService();
			service.execute(request, response);
			return;
		}else if(com.equals("/aReply.do")) {
			service = new AReplyService();
			service.execute(request, response);
			return;
		}else if(com.equals("/aLike.do")) {
			service = new ALikeService();
			service.execute(request, response);
			return;
		}else if(com.equals("/aReplydelete.do")) {
			service = new AReplyDelete();
			service.execute(request, response);
			return;											//여기부터 정보게시판
		}else if(com.equals("/iwrite.do")) {
			service = new IWriteService();
			service.execute(request, response);
			printW(request, response);
			return;
		}else if(com.equals("/iwriteView.do")) {
			viewPage = "iwriteView.jsp";
		}//가즈아
		else if(com.equals("/iListView.do")) {
			service = new IListService();
			service.execute(request, response);
			viewPage = "iListView.jsp";
		}else if(com.equals("/iContentView.do")) {
			service = new IContentService();
			service.execute(request, response);
			service = new IReplyViewService();
			service.execute(request, response);
			service = new IReplyListService();
			service.execute(request, response);
			service = new DownloadViewService();
			service.execute(request, response);
			viewPage = "iContentView.jsp";
		}else if(com.equals("/iDelete.do")) {
			service = new IDeleteService();
			service.execute(request, response);
			viewPage = "iListView.do?page="+curPage;
		}else if(com.equals("/iModifyView.do")){
			service = new IModifyViewService();
			service.execute(request, response);
			viewPage = "iModifyView.jsp";
		}else if (com.equals("/iModify.do")){
			service = new IModifyService();
			service.execute(request, response);
			service = new IContentService();
			service.execute(request, response);
			return;
		}else if(com.equals("/iReply.do")) {
			service = new IReplyService();
			service.execute(request, response);
			return;
		}else if(com.equals("/iLike.do")) {
			service = new ILikeService();
			service.execute(request, response);
			return;
		}else if(com.equals("/iReplydelete.do")) {
			service = new IReplyDelete();
			service.execute(request, response);
			return;
		}else if(com.equals("/DownloadAction.do")) {
			viewPage = "DownloadAction.jsp";
		}else if(com.equals("/toGoCompany.do")) {
			viewPage = "toGoCompany.jsp";
		}else if(com.equals("/SelectAllMemberService.do")) {
			service = new SelectAllMemberService();
			service.execute(request, response);
			return;
		}else if(com.equals("/StopMemberService.do")) {
			service = new StopMemberService();
			service.execute(request, response);
			return;
		}else if(com.equals("/blackCheck.do")) {
			service = new blackCheckService();
			service.execute(request, response);
			printW(request, response);
			return;
		}else if(com.equals("/BanMemberService.do")) {
			service = new BanMemberService();
			service.execute(request, response);
			printW(request, response);
			return;
		}else if(com.equals("/FmostWriterService.do")) {
			service = new FmostWriterService();
			service.execute(request, response);
			return;
		}else if(com.equals("/FmostReplyService.do")) {
			service = new FmostReplyService();
			service.execute(request, response);
			return;
		}else if(com.equals("/ImostWriterService.do")) {
			service = new ImostWriterService();
			service.execute(request, response);
			return;
		}else if(com.equals("/ImostReplyService.do")) {
			service = new ImostReplyService();
			service.execute(request, response);
			return;
		}else if(com.equals("/chatView.do")) {
			viewPage = "chatView.jsp";
		}else if(com.equals("/SnsLoginService.do")) {
			service = new SnsLoginService();
			service.execute(request, response);
			printW(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	public void printW(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String jsonData= "";
		jsonData = (String)request.getAttribute("data");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(jsonData);
		writer.close();
	}
	
	private void logoutOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("logout");
		
		
		HttpSession session = request.getSession();
		session.invalidate();
		
	}
}
