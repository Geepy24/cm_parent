package management.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.mysql.jdbc.log.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

@ParentPackage("json-default")
@Namespace("/Article")
@Result(name="fail",location="/fail.jsp")
public class draftAction extends ActionSupport implements ModelDriven<Draft> {
	
	@Autowired
	private IArticleService articleService ;
	private Draft draft = new Draft() ;
	private String returndata ;
	
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//��ǰ�û�
	private User user =(User) session.getAttribute("loginInfo")  ;
	private List<Draft> drafts ;
	//��ҳ�����Ŀ��
	private static int MAXRESULTS = 10 ;
	//��ǰҳ��
	private int currentPage ;
	
	
	//�ݸ���ҳ����ת
	private int toPage ;
	
	@Override
	public Draft getModel() {
		return draft;
	}


	//-----------getter/setter------------------
	

	public IArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}	

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Draft> getDrafts() {
		return drafts;
	}

	public void setDrafts(List<Draft> drafts) {
		this.drafts = drafts;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}


	//-----------action--------------------
	/**
	 * 	����ݸ������ݸ����action
	 * 	���ݸ��id����
	 */
	@Action(value="saveTemp",results= {@Result(name="success",type="json",params= {"root","returndata"})})
	//location="/WEB-INF/jsp/management/article/tempList.jsp"
	public String tempArticle() {
		System.out.println("�ݸ���");
		System.out.println(draft);
		System.out.println(user);
		
		draft.setUser(user);

		articleService.saveDraft(draft);
		
		System.out.println(draft.getDraId());
		returndata = "{\"draId\" :  \""+draft.getDraId()+"\",\"userId\" : \""+draft.getUser().getUserId()+"\"}" ;
		
		return SUCCESS ;
	}

/**
 * 	�ݸ����б����ԴӲ˵��������������ݣ�
 * 			Ҳ���Դӱ������½�������������ΪauthorID��draId��
 * 	��Ҫ�Ľ�dao��ķ�ҳ����
 * @return
 */
	@Action(value="toDraftList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
	public String toList() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		System.out.println("ҳ����Դ��"+request.getRequestURL());
		
		currentPage = 1 ;
		
 		//Draft draftTemp = null ;
		//draft.setAuthorId(user.getUserId());
		draft.setUser(user);
		System.out.println(draft.getDraId());
		//�����ְ취��ֵջ���˵Ķ�����ֵ�����ı�ʵ����ĵ�ַ
		//draftTemp = articleService.findDraftById(draft.getDraId());
		//System.out.println(draft.getAuthorId());
		System.out.println(draft.getUser().getUserId());
		//draft.setArtTitle(draftTemp.getArtTitle());
		//draft.setArtContent(draftTemp.getArtContent());
		//draft.setLastMod(draftTemp.getLastMod());
		//ʹ��hibernateTemplate��findByExample����
		drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
		
		//���вݸ�
		Long totalItems = articleService.AllDraftNumber(draft.getUser().getUserId()) ;
		Long totalPages ;
		//��ҳ��
		if(0 == totalItems) {
			totalPages = new Long(1);
		}else if(0 == totalItems%10) {
			totalPages = totalItems/10 ;
		}else {
			totalPages = (totalItems/10) + 1  ;
		}
		//�Ž�session
		session.setAttribute("totalPages", totalPages);
		return SUCCESS;
	}
	
	//��һҳ�����б�
		@Action(value="nextDraft",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
		public String nPage() {
			
			//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			draft.setUser(user);
			
			
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			if(drafts.size() == 0) {
				return "fail" ;
			}
			
			return SUCCESS ;
		}
		//��һҳ�����б�
		@Action(value="preDraft",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
		public String pPage() {
			
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			if(currentPage <= 0) {
				return "fail" ;
			}
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			
		
			
			return SUCCESS ;
		}
		//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
		@Action(value="selectDraftPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/draftList.jsp")})
		public String selectPage() {
			
			System.out.println(toPage);
			currentPage = toPage ;
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft,toPage, MAXRESULTS) ;
			
		
			
			return SUCCESS ;
		}
		/**
		 * 	ɾ���ݸ�
		 */
		@Action(value="deleDraft",results= {@Result(name="success",type="chain",location="toDraftList")})
		public String deleteDraft() {
			System.out.println(draft.getDraId());
			articleService.deleteDraft(draft.getDraId()) ;
			
			return SUCCESS ;
		}
		/**
		 * ��ת���༭ҳ��
		 */
		@Action(value="toDraEdit",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/editArticle.jsp")})
		public String toDraftEdit() {
			
			
			System.out.println(draft.getDraId());
			session.setAttribute("editDraftId", draft.getDraId());
			
			
			return SUCCESS ;
		}
		/**
		 * 	�༭�ݸ�
		 * @return
		 */
		@Action(value="draEdit",results= {@Result(name="success",type="json",
				params= {"root","returndata"})})
		public String draEdit() {
			Draft draftTemp = new Draft() ;
			
			System.out.println(session.getAttribute("editDraftId"));

			draftTemp = articleService.findDraftById((Integer)session.getAttribute("editDraftId")) ;
			
			Map<String, String> map = new HashMap<>() ;
			map.put("draId", String.valueOf(draftTemp.getDraId())) ;
			map.put("artContent", draftTemp.getArtContent()) ;
			map.put("artTitle",	draftTemp.getArtTitle()) ;
			map.put("authorName", user.getUserName()) ;
			map.put("lastMod", draftTemp.getLastMod()) ;
			
			JSONObject json = JSONObject.fromObject(map) ;
			returndata = json.toString() ;
			
			System.out.println(returndata);
			return SUCCESS ;
		}
	
}
