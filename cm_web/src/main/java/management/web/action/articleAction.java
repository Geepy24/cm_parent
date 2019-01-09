package management.web.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * ���µĶ�����
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
@Result(name="fail",location="/fail.jsp")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	
	@Autowired
	IArticleService articleService ;
	
	//ģ������
	private Article article = new Article() ;

	//��������
	private List<Article> articles ;
	private int currentPage ;
	private int toPage ;
	private String delTime ;
	//��ҳ����Ŀ��
	private static int MAXRESULTS = 10;
	private String returndata ;
	private JSONObject json ;
	//private int article_Id ;
	//����
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	
	@Override
	public Article getModel() {

		return article;
	}
	
	public String getDelTime() {
		return delTime;
	}

	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

//	public int getArticle_Id() {
//		return article_Id;
//	}
//
//	public void setArticle_Id(int article_Id) {
//		this.article_Id = article_Id;
//	}
	public Article getArticle() {
		return this.article;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

//-------------------��������-------------------------------



	//�ö����ദ�����Ա�������ύ
	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","article"})
	public String articleHandler() {
		//��ǰ�û���������Ա
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("��ǰҳ���û�"+user);
		System.out.println("ģ������������"+article);
		
		article.setUser(user);
		article.setAdsName(user.getUserName());
		article.setLastMod(article.getPubTime());
		
		articleService.saveArticle(article);
		
		System.out.println(article);
		
		return "handle" ;
	}
	
	
	//������չʾģ������ʾ���£���Ҫ�Ĳ���Ϊ����ID
	@Action(value="showArticle",results={@Result(name="success",location="/WEB-INF/jsp/management/article/show.jsp")})
	public String showPage() {
		
		//ģ������������ֻ��artId������id����article
		//System.out.println(article);
		 
		article = articleService.findById(article.getArtId()) ;
		System.out.println("��ѯ�����"+article);
		//��ѯ�����ֵ������ֵջ�е�article������ûֵ
		
		return SUCCESS ;
	}

	//�����б���ҳ���Һ���ʾ����ʾ������ҳ
	@Action(value="articleList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String showArticleList() {
		//��һҳ��ÿҳʮ��
		 
		 currentPage = 1 ;
		 articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		 //����ҳ��
		//��������
			Long totalItems = articleService.AllArticleNumber();
			Long totalArticles ;
			
			//��ҳ��
			if(0 == totalItems) {
				totalArticles = new Long(1);
			}else if(0 == totalItems%10) {
				totalArticles = totalItems/10 ;
			}else {
				totalArticles = (totalItems/10) + 1  ;
			}
			//�Ž�session
			session.setAttribute("totalArticles",totalArticles );
		 
		 
		 
		return SUCCESS;
		
	}
	//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
	@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String selectPage() {
		
		System.out.println(toPage);
		currentPage = toPage ;
		articles = articleService.findAllArticle(toPage, MAXRESULTS) ;
		
		
		return SUCCESS ;
	}
	//��һҳ�����б�
	@Action(value="nextPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String nPage() {
		
		//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
		int temp = currentPage ;
		temp = temp + 1 ;
		currentPage = temp ;
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		if(articles.size() == 0) {
			return "fail" ;
		}
		
		return SUCCESS ;
	}
	//��һҳ�����б�
	@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String pPage() {
		
		int temp = currentPage ;
		temp = temp - 1 ;
		currentPage = temp ;
		if(currentPage <=0) {
			return "fail" ;
		}
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		
		return SUCCESS ;
	}

	/**
	 * 	ҳ�����ʱ�����š���Դ��Ŀ���������
	 */
	@Action(value="indexArticle",results= {@Result(name="success",type="json",
					params= {"root","returndata"})})
	public String index() {
		System.out.println("��ҳ����");
		//���Ժ�����query.list()��ס��û�з�Ӧ
		//��������⣬û����ûֵ��������spring���񣬳������Զ��ع�
		//Ӧ�ò���������£����ܸյ���ҳ��û��hibernateû��session
		//������session������
		articles = articleService.findAllArticle(1, MAXRESULTS) ;
		Map<String , String> map = new LinkedHashMap<>() ;
		for(int i = 0 ; i<articles.size() ; i++) {
			map.put(articles.get(i).getArtId()+"", articles.get(i).getArtTitle()+"."+articles.get(i).getPubTime()) ;  
		}
		//�ټ�����������
		JSONObject json = JSONObject.fromObject(map) ;
		System.out.println(map);
		System.out.println(json);
		returndata = json.toString() ;
		System.out.println(returndata);
		
		return SUCCESS ;
	}
	
	/**
	 * 	��������
	 */
	@Action(value="artDetail",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/artDetail.jsp")})
	public String articleDetail() {
		Article articleTemp = new Article() ;
		System.out.println(article.getArtId());
		articleTemp = articleService.findById(article.getArtId()) ;
		article.setAdsName(articleTemp.getAdsName());
		article.setArtContent(articleTemp.getArtContent());
		article.setArtTitle(articleTemp.getArtTitle());
		article.setUser(articleTemp.getUser());
		article.setLastMod(articleTemp.getLastMod());
		article.setPubTime(articleTemp.getPubTime());
		
		
		System.out.println(article);
		return SUCCESS ;
	}
	/**
	 * ɾ������,�ȼ������վ��������ɾ��
	 */
	@Action(value="deleteArticle",results= {
			@Result(name="success",type="json")
			
	})
	public String delAction() {
		System.out.println("json������"+article.getArtId()+"-"+delTime);
		
		Article articleTemp = articleService.findById(article.getArtId()) ;		
		Dustbin dustbin = new Dustbin() ;
		dustbin.setArtContent(articleTemp.getArtContent()) ;
		dustbin.setUser(articleTemp.getUser());
		dustbin.setArtId(articleTemp.getArtId());
		dustbin.setDelTime(delTime);
		dustbin.setArtTitle(articleTemp.getArtTitle());
		
		articleService.saveDustbin(dustbin);
		articleService.deleteArticle(articleTemp);
		
		return SUCCESS ;
	}
	//�޸�����
	@Action(value="updateArt",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String updataArticle() {
		System.out.println("Ҫ�޸ĵ����£�"+article);
		//�����޸��˱��⣬����,�ض��޸���lastMod
		String title = article.getArtTitle() ;
		String content = article.getArtContent() ;
		String lastMod = article.getLastMod() ;
		//���ԭ����
		article = articleService.findById(article.getArtId()) ;
		article.setArtTitle(title);
		article.setArtContent(content);
		article.setLastMod(lastMod);
		articleService.updateArticle(article);
		returndata = "success" ;
		
		return SUCCESS ;
	}
	//�޸Ĳݸ�
	@Action(value="updateDra",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String updataDra() {
		System.out.println("Ҫ�޸ĵĲݸ壺"+article);
		//����ԭ����
		Draft draft = articleService.findDraftById(article.getArtId()) ;
		draft.setArtContent(article.getArtContent());
		draft.setArtTitle(article.getArtTitle());
		draft.setLastMod(article.getLastMod());
		
		articleService.updateDraft(draft) ;
		returndata = "success" ;
		return SUCCESS ;
	}
	//ɾ���ݸ�
	@Action(value="delDraft",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String deleteDraft() {
		
		System.out.println("Ҫɾ����id"+article.getArtId());
		articleService.deleteDraft(article.getArtId());
		return SUCCESS ;
		
	}
	//ɾ������վ
	@Action(value="delDustbin",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String deleteDustbin() {
		
		System.out.println("Ҫɾ����id"+article.getArtId());
		articleService.deleteDustbin(article.getArtId());
		return SUCCESS ;
		
	}
}	
