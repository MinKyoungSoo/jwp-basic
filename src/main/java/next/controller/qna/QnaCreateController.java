package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaCreateController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        if (!UserSessionUtils.isLogined(session)) {
            throw new IllegalStateException("글쓰기는 로그인한 유저만 입력 가능합니다.");
        }

        try {
            questionDao.insert(
                new Question(((User)session.getAttribute("user")).getUserId(), 
                request.getParameter("title"), 
                request.getParameter("contents"))
            );
            return jspView("redirect:/");
        } catch (DataAccessException e) {
            throw new IllegalStateException("게시판 글 등록시 오류가 발생하였습니다.\n" + e.getMessage());
        }
    }
}
