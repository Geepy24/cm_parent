package com.cm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.Movie;
import com.cm.domain.MovieCheck;
import com.cm.domain.Picture;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.cm.utils.jsonUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 	���ڿ��Ƹ��˿ռ�ģ��
 * 	typeʹ��json��ע�⣬ʹ��json����������ת����������action�ٻص�ҳ����ʱ������ı�ֵջ��ֵ��
 * 	Ҫ�ֶ���ջ
 * @author mac
 *
 */

@Namespace("/Persional")
@ParentPackage("p1")
@InterceptorRefs({ @InterceptorRef("loginDefault") })
@Results({ @Result(name = "login", type = "chain", location = "login", params = { "namespace", "/User" }),
	@Result(name = "fail", location = "/fail.jsp") })
public class persionalSpaceAction extends ActionSupport implements ModelDriven<User>{
	
	
	//	request����
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private String returndata ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	@Autowired
	IUserService userService ;
	@Autowired
	IArticleService articleService ;
	@Autowired
	IResourceService resourceService ;
	
	
	private  static final Integer MAXRESULTS = 10 ;
	private String pageRef ;
	private int currentPage ;
	private int toPage ;
	private List<Article> articles ;
	private List<Draft> drafts  ;
	private List<Dustbin> dustbins  ;
	private List<Resource> resources  ;
	private List<Picture> pictures ;
	private List<Movie> movies ;
	private String jsonFlag ;
	private int jsonId ;
	private List<PictureCheck> pictureChecks ;
	private List<MovieCheck> movieChecks ;
	
	
	@Override
	public User getModel() {
		return user;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Draft> getDrafts() {
		return drafts;
	}

	public void setDrafts(List<Draft> drafts) {
		this.drafts = drafts;
	}

	public List<Dustbin> getDustbins() {
		return dustbins;
	}

	public void setDustbins(List<Dustbin> dustbins) {
		this.dustbins = dustbins;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	


	

	public String getJsonFlag() {
		return jsonFlag;
	}

	public void setJsonFlag(String jsonFlag) {
		this.jsonFlag = jsonFlag;
	}

	public int getJsonId() {
		return jsonId;
	}

	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public String getPageRef() {
		return pageRef;
	}

	public void setPageRef(String pageRef) {
		this.pageRef = pageRef;
	}
	
	
	
public List<PictureCheck> getPictureChecks() {
		return pictureChecks;
	}

	public void setPictureChecks(List<PictureCheck> pictureChecks) {
		this.pictureChecks = pictureChecks;
	}

	public List<MovieCheck> getMovieChecks() {
		return movieChecks;
	}

	public void setMovieChecks(List<MovieCheck> movieChecks) {
		this.movieChecks = movieChecks;
	}

/**-----------------------------��������----------------------------**/
	//��ҳ��ת�����Աҳ��
	@Action(value="comein",results= {
			@Result(name="success",location="/UI2/index.html")
			
	})
	public String comeIn() {
		
		System.out.println("��ǰ�û�������˿ռ䣺"+user);		
		
		
		return SUCCESS ;
		
	}
	@Action(value="sendUser",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String sendUser() {
		
		returndata = user.getUserName() ;
		
		return SUCCESS ;
	}
	//�����������ҳ�ı��
	@Action(value="indexArticles",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String indexArticles() {
		currentPage = 1 ;
		//��ҳ����
		Article article = new Article() ;
		article.setUser(user);
		articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
		request.getSession().setAttribute("currentPage", currentPage);
		
		//json�ַ����ĸ�ʽ��{"i" : "artTitle+###+pubTime###lastMod###artId"}
		returndata = jsonUtils.artListToJsonString(articles) ;
		
		return SUCCESS ;
	}
//	//ȥ��������
//	@Action(value="toArtList",results= {@Result(name="success",location="/UI/artList.jsp")})
//	public String toArticleList() {
//		currentPage = 1 ;
//		request.getSession().setAttribute("currentPage", currentPage);
//		return SUCCESS;
//	}
	//��������
	@Action(value="userAddArt",results= {
			@Result(name="success",location="/UI/editor.jsp")
	})
	public String userAddArticle() {
		return SUCCESS;
	}
//	//ȥ�ݸ���
//	@Action(value="toDraftList",results= {@Result(name="success",location="/UI/draftList.jsp")})
//	public String toDraftList() {
//		
//		
//		return SUCCESS ;
//	}
	//�òݸ����ݸ���ı��
	@Action(value="draList",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String draftList() {
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		Draft draft = new Draft() ;
		draft.setUser(user);
		
		//��ҳ����
		drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
		
		//json�ַ����ĸ�ʽ��{"i" : "artTitle###lastMod###draId"}
		returndata = jsonUtils.draListToJsonString(drafts) ;
		
		return SUCCESS ;
	}
	
	
//	//ǰ������վ
//	@Action(value="toDustbinList",results= {@Result(name="success",location="/UI/dustbinList.jsp")})
//	public String toDustbinList() {
//		
//		return SUCCESS ;
//	}
	//�û���վ���ݸ���ı��
		@Action(value="dustList",results= {
				@Result(name="success",type= "json",params= {"root","returndata"})
		})
		public String dustbinList() {
			
			currentPage = 1 ;
			request.getSession().setAttribute("currentPage", currentPage);
			Dustbin  dustbin = new Dustbin() ;
			dustbin.setUser(user);
			
			//��ҳ����
			dustbins = articleService.findAllDustbinByUser(dustbin,currentPage,MAXRESULTS) ;
			
			//json�ַ����ĸ�ʽ��{"i" : "artTitle###delTime###dustId"}
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			
			return SUCCESS ;
		}
	
	//��һҳ
	@Action(value="prePage",results= {
			@Result(name="success",type="json",params= {"root","returndata"}),
			@Result(name="fail",type="json",params= {"root","returndata"})
	})
	public String previousPage() {
		//currentPage,pageRef
		currentPage = (int) request.getSession().getAttribute("currentPage") ;
		currentPage = currentPage - 1 ;
		if(currentPage <=0 ) {
			returndata = "error : û�и����ˣ�" ;
			return "fail" ;
		}
		
		if(pageRef.equals("dustbin")) {
			Dustbin dustbin = new Dustbin() ;
			dustbin.setUser(user);
			dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		if(pageRef.equals("article")) {
			Article article = new Article() ;
			article.setUser(user);
			articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.artListToJsonString(articles) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		if(pageRef.equals("draft")) {
			Draft draft = new Draft() ;
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.draListToJsonString(drafts) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("movies")) {
			resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.movieListToJsonString(resources) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("photos")) {
			resources = resourceService.findAllResource("pic", currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("MCs")) {
			movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.mcListToJsonString(movieChecks) ;
	 		request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("PCs")) {
			pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		
		
		
		return "fail" ;
	}
		
	//��һҳ
	@Action(value="nPage",results= {
			@Result(name="success",type="json",params= {"root","returndata"}),
			@Result(name="fail",type="json",params= {"root","returndata"})
	})
	public String nextPage() {
		//currentPage,pageRef
			currentPage = (int) request.getSession().getAttribute("currentPage");
			System.out.println("ҳ���ǣ�"+pageRef);
			currentPage = currentPage + 1 ;
				
				System.out.println("��һҳ"+currentPage);
				
				if(pageRef.equals("dustbin")) {
				Dustbin dustbin = new Dustbin() ;
				dustbin.setUser(user);
				dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
					if(dustbins.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.dsutListToJsonString(dustbins) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("article")) {
					Article article = new Article() ;
					article.setUser(user);
					articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
					if(articles.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.artListToJsonString(articles) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("draft")) {
					Draft draft = new Draft() ;
					draft.setUser(user);
					drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
					if(drafts.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.draListToJsonString(drafts) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("movies")) {
					
					resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
					if(resources.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.movieListToJsonString(resources) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("photos")) {
					
					resources = resourceService.findAllResource("pic", currentPage, MAXRESULTS) ;
					if(resources.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.pictureListToJsonString(resources) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("MCs")) {
					movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
					if(movieChecks.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.mcListToJsonString(movieChecks) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("PCs")) {
					pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
					if(pictureChecks.size() == 0) {
						returndata = "error : û�и����ˣ�" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
		return "fail" ;
	}
	
	
	//ǰ��ĳҳ
	@Action(value="sPage",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String selectPage() {
		currentPage = toPage ;
		System.out.println(pageRef);
		request.getSession().setAttribute("currentPage", currentPage);
		System.out.println(currentPage);
		if(pageRef.equals("article")) {
			Article article = new Article() ;
			article.setUser(user);
			articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.artListToJsonString(articles) ;
			return SUCCESS ;
		}
		if(pageRef.equals("draft")) {
			Draft draft = new Draft() ;
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.draListToJsonString(drafts) ;
			return SUCCESS ;
		}
		if(pageRef.equals("dustbin")) {
			Dustbin dustbin = new Dustbin() ;
			dustbin.setUser(user);
			dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			return SUCCESS ;
		}
		if(pageRef.equals("photos")) {
			
			resources = resourceService.findAllResource("pic", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			return SUCCESS ;
		}
		if(pageRef.equals("movies")) {
			resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.movieListToJsonString(resources) ;
			return SUCCESS ;
		}
		if(pageRef.equals("PCs")) {
			
			pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
			return SUCCESS ;
		}
		if(pageRef.equals("MCs")) {
			movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.mcListToJsonString(movieChecks) ;
			return SUCCESS ;
		}
	return "fail" ;
		
	}
		
	//ȫ��ҳ�� 
	@Action(value="pages",results= {
			@Result(name="success",type="json",params= {"root","returndata"})

	})
	public String pages() {
		Long number = 1l ;
		Long pages ;
		System.out.println("��Դ��"+pageRef);
		//����
		if(pageRef.equals("article")) {
			number = articleService.AllArticleNumber(user.getUserId()) ;
		}
		//�ݸ�
		if(pageRef.equals("draft")) {
			 number = articleService.AllDraftNumber(user.getUserId()) ;
		}
		//����վ
		if(pageRef.equals("dustbin")) {
			number = articleService.AllDustbinNumber(user.getUserId()) ;
		}
		//���,ͨ�������������
		if(pageRef.equals("photos")) {
			number = resourceService.AllResourceNumber(user, "pic") ;
		}
		//��Ƶ��ͨ�������������
		if(pageRef.equals("movies")) {
			number = resourceService.AllResourceNumber(user, "mov") ;
			System.out.println("check��"+number);
		}
		//�������б�
		if(pageRef.equals("PCs")) {
			number = resourceService.findAllPcsByUserId(user.getUserId()) ;
		}
		//��Ƶ����б�
		if(pageRef.equals("MCs")) {
			number = resourceService.findAllMcsByUserId(user.getUserId()) ;
		}
		
		if(0 == number) {
			pages = 1l ;
		}else if( 0 == number % MAXRESULTS ) {
			pages = number / MAXRESULTS ;
		}else {
			pages = number / MAXRESULTS +1 ;
		}
		System.out.println(number);
		System.out.println("��ҳ����"+pages);
		returndata = String.valueOf(pages) ;
		return SUCCESS ;
	}
	//��ǰҳ��
	@Deprecated
	@Action(value="thePage",results= {
			@Result(name="success",type="json",params= {"root","returndata"})

	})
	public String thePage() {
		
		System.out.println(pageRef);
		if(pageRef.equals("previous")) {
			
		}
		if(pageRef.equals("next")) {
			
		}
		
		returndata = String.valueOf((int)request.getSession().getAttribute("currentPage")+1) ;
		
		return SUCCESS ;
	}
	
	
	//�༭���£��������£�����վ���ݸ塣�ݸ�ı༭��saveӦ�����޸ģ����²������������ø���
	@Action(value="edit",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String Edit() {
		System.out.println("Ҫ�༭������:"+jsonFlag+"-"+jsonId);
		Map<String, String> map = new HashMap<String, String>() ;
		String title = "" ;
		String content = "" ; 
		if(jsonFlag.equals("article")) {
			Article article = articleService.findById(jsonId) ;
			title = article.getArtTitle() ;
			content = article.getArtContent() ;
			
		}if(jsonFlag.equals("draft")) {
			Draft draft = articleService.findDraftById(jsonId) ;
			title = draft.getArtTitle() ;
			content = draft.getArtContent() ;
		}if(jsonFlag.equals("dustbin")) {
			Dustbin dustbin = articleService.findDustbinById(jsonId) ;
			title = dustbin.getArtTitle() ;
			content = dustbin.getArtContent() ;
			
		}
		map.put("artTitle", title) ;
		map.put("artContent", content) ;
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		returndata = jsonObject.toString() ;
		return SUCCESS ;
		
	}
	//----------------------------��ʾ�û�����Ƶ��ͼƬ�б�----------------------
	//��ʾ�û�����Ƶ�б�
	@Action(value="movList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
			
	})
	public String movieList() {
		
		currentPage = 1 ;
		resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
		
		returndata = jsonUtils.movieListToJsonString(resources) ;
		
		return SUCCESS ;
	}
	//��ʾ�û���ͼƬ�б�
		@Action(value="picList",results= {
				@Result(name="success",type="json",params= {"root","returndata"})
				
		})
		public String pictureList() {
			
			currentPage = 1 ;
			resources = resourceService.findAllResource("pic", user.getUserId(), currentPage, MAXRESULTS) ;
			
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			
			return SUCCESS ;
		}
	
	//----------------------------��ʾ�û�����Ƶ��ͼƬ����б�----------------------
	//��ʾ�û��Ĵ������Ƶ�б�
		@Action(value="userMcList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
				
	})
	public String userMovieCheckList() {
			
		currentPage = 1 ;
		movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			
		returndata = jsonUtils.mcListToJsonString(movieChecks) ;
		return SUCCESS ;
	}	
	//��ʾ�û��Ĵ����ͼƬ�б�
	@Action(value="userPcList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
			
	})
	public String userPictureCheckList() {
		
		currentPage = 1 ;
		pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
		
		returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
		
		return SUCCESS ;
	}
}
