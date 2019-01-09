package management.web.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.MediaPreview;
import com.cm.domain.Movie;
import com.cm.domain.Picture;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.utils.movieUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ����ϵͳ����Դ���ʶ�����
 * @author mac
 *
 */


@Namespace("/Resource")
@ParentPackage("p1")
@InterceptorRefs({@InterceptorRef("loginDefault")})
@Results({
	@Result(name="login",type="chain",location="login",params= {"namespace","/User"}),
	@Result(name="fail",location="/fail.jsp")
})
public class resourceAction extends ActionSupport implements Serializable, ModelDriven<Resource> {

	@Autowired
	IResourceService resourceService ;
	Resource resource = new Resource() ;
	private File upload ;
	private String uploadFileName ;
	private List<Resource> resources ;
	private int currentPage ;
	private int toPage ;
	//����ͼ������
	private String mpName ;
	
	//��ҳ����Ŀ��
		private static int MAXRESULTS = 10;
	private String tag ;
	
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =(User) session.getAttribute("loginInfo")  ;
	
	@Override
	public Resource getModel() {
		return resource;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getMpName() {
		return mpName;
	}

	public void setMpName(String mpName) {
		this.mpName = mpName;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * ת��������Դ��ͼ
	 * @return
	 */
	@Action(value="uploadRes",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/resUpload.jsp")
			
	})
	public String uploadResource() {
		
		return SUCCESS ;
	}
	/**
	 * ������Դ��
	 * 	����ͼƬ�ļ���ֱ�ӱ��漴��
	 * 	������Ƶ�ļ���Ҫ������Ƶ����ͼ������������ͼ��Ϣ������ͼ�ı���
	 * 	ģ����������resTag,resCom,pubTime
	 * @return
	 * @throws IOException
	 */
	@Action(value="addRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/addSuccess.jsp")})
	public String addResource() throws IOException {
		
		System.out.println("�ϴ���Դ");
		//��ǰ��Ŀ·��t
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
		//�ϴ����ļ�����uploadFileName
		System.out.println(uploadFileName);
		//�ϴ����ļ�
		System.out.println(upload.toString());
		
		//�ϴ����ļ�·��
		String filePath = "" ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		
		System.out.println("��Դ���"+resource.getResTag());
		
		
		if(resource.getResTag().equals("mov")) {
			//�浽��Ƶ�ļ���
			filePath = temp[0]+"mov" ;
			//������
			request.getSession().setAttribute("picOrmov","mov");
		}else {
			//�浽ͼƬ�ļ���
			 filePath = temp[0]+"pic" ;
			 request.getSession().setAttribute("picOrmov","pic");
		}
		//���浽���ļ��е�λ��
		System.out.println(filePath);
		
		//�ļ��в������򴴽�
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
        
		//��ҳ�洫����������ͨ��FileUtils�������ļ�·����,�ļ�����ǰ�˴����uploadFileName
        //upload��ҳ�洫�������ļ���file���ļ��У�uploadFileName���ļ�����
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        System.out.println("Ҫ������ļ�����·����"+filePath);

        //���浽���ݿ���
        //chrome�ϴ�ʧ�ܣ�����Provisional headers are shown,�����������
        
        
        //uploadFileName���ļ�����xxx.mp4/xxx.jpg
        //filePath+"/"+uploadFileName���������ļ���·������xxx/xxx/xxx.jpg����xxx/xxx/xxx.mp4
 
        //��ͼƬ����ֱ�ӱ���
        if(resource.getResTag().equals("pic")) {
        	Picture picture = new Picture() ;
        	
        	picture.setPicUri(filePath+"/"+uploadFileName);
        	picture.setPicName(uploadFileName);
        	resource.setAdsId(user.getUserId());
        	resource.setUser(user);
        	resource.setPicture(picture);
        	//��������
        	resourceService.saveResource(resource);
            System.out.println("������ͼƬ��"+resource);
        	return SUCCESS ; 
        	
        }else {
        	//��������Ƶ����Ҫ��������ͼ��ͬʱ������ͼid��Ϊ�������
    		//��������ͼ������������
        
			Movie movie = new Movie() ;
			movie.setMovUri(filePath+"/"+uploadFileName);
			movie.setMovName(uploadFileName);
			
			//����ͼ���ļ���
			String mpPath = temp[0]+"media_preview" ;
			File mpFile = new File(mpPath); 
	        if(!mpFile.exists()){ 
	            mpFile.mkdir(); 
	        }
	        //Ҫ�����jpg�ļ���uri���������jpg������uri
	        String mpName = movie.getMovName().split("\\.")[0] + ".jpg";
	        String mpUri = mpPath+"/"+mpName;
	        
	        System.out.println(mpUri+"---"+mpName);
	        //����ͼ������
			movieUtils.handler(movieUtils.ffmpegPath, movie.getMovUri(),mpUri );
			
			MediaPreview mediaPreview = new MediaPreview() ;
			mediaPreview.setMpName(mpName);
			mediaPreview.setMpUri(mpUri);
			
			//��movie���������Ȼ��������
			movie.setMediaPreview(mediaPreview);
			
			
        	resource.setUser(user);
			resource.setAdsId(user.getUserId());
			resource.setMovie(movie);
			
			System.out.println("��������Ƶ��"+resource);
			System.out.println("����������ͼ��"+mediaPreview);
    		
			//resource��������movie��movie�ټ�������mediaPreview
			resourceService.saveResource(resource);
			return SUCCESS ; 
        }
		
	}
	
	/**
	 * �˵���ͼƬ/��Ƶ�б�ѡ��
	 */
	@Action(value="list",results= {@Result(name="success",type="chain",location="resList")})
	public String toList() {
		System.out.println(resource.getResTag());
		if (resource.getResTag().equals("pic")) {
			request.getSession().setAttribute("picOrmov", "pic");
		}else {
			request.getSession().setAttribute("picOrmov", "mov");
			
		}
		
		
		return SUCCESS ;
		
	}
	
	/**
	 * 	��Դ�б�
	 * @return
	 */
	@Action(value="resList",results= {
			@Result(name="picture",location="/WEB-INF/jsp/management/resource/picList.jsp"),
			@Result(name="movie",location="/WEB-INF/jsp/management/resource/movList.jsp")
	})
	public String resourceList() {
		
		currentPage = 1 ;
		try {
			tag = request.getSession().getAttribute("picOrmov").toString();
		}catch(NullPointerException e){
			tag = resource.getResTag() ;
		}
		
		 //����ҳ��
		//��������
			Long totalItems = resourceService.AllResourceNumber(tag) ;
			Long totalResource ;
			
			//��ҳ��
			if(0 == totalItems) {
				totalResource = new Long(1);
			}else if(0 == totalItems%10) {
				totalResource = totalItems/10 ;
			}else {
				totalResource = (totalItems/10) + 1  ;
			}
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			if(tag.equals("mov")) {
				
				return "movie" ;
			}
			
			//System.out.println(resources.get(0));
			session.setAttribute("totalResource",totalResource );
			
			
		return "picture" ;
	}
	//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
		@Action(value="selectPage",results= {
				@Result(name="picture",location="/WEB-INF/jsp/management/resource/picList.jsp"),
				@Result(name="movie",location="/WEB-INF/jsp/management/resource/movList.jsp"),
				@Result(name="fail",location="/fail.jsp"
						
						)})
		public String selectPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			System.out.println(toPage);
			currentPage = toPage ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			if(tag.equals("pic")) {
				return "picture" ;
			}
			if(tag.equals("mov")) {
				return "movie" ;
			}
			
			return SUCCESS ;
		}
	//��һҳ��Դ�б�
		@Action(value="nextPage",results= {
				@Result(name="picture",location="/WEB-INF/jsp/management/resource/picList.jsp"),
				@Result(name="movie",location="/WEB-INF/jsp/management/resource/movList.jsp"),
				@Result(name="fail",location="/fail.jsp")
		})
		public String nPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			System.out.println(resources.size());
			if(0 == resources.size()) {
				return "fail" ;
			}
			if(tag.equals("pic")) {
				return "picture" ;
			}
			if(tag.equals("mov")) {
				return "movie" ;
			}
			return "fail" ;
		}
		//��һҳ��Դ�б�
		@Action(value="prePage",results= {
				@Result(name="picture",location="/WEB-INF/jsp/management/resource/picList.jsp"),
				@Result(name="movie",location="/WEB-INF/jsp/management/resource/movList.jsp"),
				@Result(name="fail",location="/fail.jsp")
		})
		public String pPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			if(currentPage <= 0) {
				return "fail" ;
			}
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			if(tag.equals("pic")) {
				return "picture" ;
			}
			if(tag.equals("mov")) {
				return "movie" ;
			}
			return "false" ;
		}
		//��Դ����
		@Action(value="resDetail",results= {
				@Result(name="picture",location="/WEB-INF/jsp/management/resource/picDetail.jsp"),
				@Result(name="movie",location="/WEB-INF/jsp/management/resource/movDetail.jsp")
		})
		public String resourceDetail() {
			
			if(request.getAttribute("resId") != null) {
				resource.setResId((Integer)request.getAttribute("resId"));
			}
			System.out.println("Ҫ��ʾ����Դ��id"+resource.getResId());
			Resource resourceTemp = new Resource() ;
			resourceTemp = resourceService.findResourceById(resource.getResId()) ;
			resource.setAdsId(resourceTemp.getAdsId());
			resource.setPubTime(resourceTemp.getPubTime());
			resource.setResCom(resourceTemp.getResCom());
			resource.setUser(resourceTemp.getUser()) ;
			resource.setResTag(resourceTemp.getResTag());
			resource.setMovie(resourceTemp.getMovie());
			resource.setPicture(resourceTemp.getPicture());
			if(resource.getResTag().equals("mov")) {
				
				return "movie" ;
			}
			
			return "picture" ;
		}
		//��һ��ͼƬ����Ƶ
		@Action(value="nextRes",results= {@Result(name="success",type="chain",location="resDetail")})
		public String nextResource() {
			tag = resource.getResTag() ;
			System.out.println("��һ��Ҫ�ҵ���Դ���"+tag);
			System.out.println("��ǰ��resId��Ҫ������һ��id"+resource.getResId()); 
			try {
				//������һ��ͬһ�����͵���Դid
				Integer nextId = resourceService.nextResourceId(resource.getResId(),tag) ;
			
				resource.setResId(nextId);
				//����resDetail.action
				request.setAttribute("resId", nextId);
				
				return SUCCESS ;
			}catch (Exception e) {
				System.out.println("��������һ��ͬ������Դ");
				return "fail" ;
			}
			
		}
		//��һ��ͼƬ����Ƶ
		@Action(value="preRes",results= {
				@Result(name="success",type="chain",location="resDetail")
		})
		public String preResource() {
			tag = resource.getResTag() ;
			System.out.println("��һ��Ҫ�ҵ���Դ���"+tag);
			System.out.println("��ǰ��resId��Ҫ������һ��id"+resource.getResId()); ;
			try {
				Integer preId = resourceService.preResourceId(resource.getResId(),tag) ;
				System.out.println(preId);
				//����resDetail.action
				request.setAttribute("resId", preId);
				return SUCCESS ;
			} catch (Exception e) {
				return "fail" ;
			}
			
		}
		
		@Action(value="delResource",results= {@Result(name="success",type="json")})
		public String deleteResource() {
			
			System.out.println("Ҫɾ����id"+resource.getResId());
			resourceService.deleteResource(resource.getResId());
			
			
			return SUCCESS ;
		}
		
		
		
}
