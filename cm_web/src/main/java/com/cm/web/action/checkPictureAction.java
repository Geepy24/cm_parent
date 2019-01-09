package com.cm.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.cm.domain.Picture;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.cm.utils.WebUtils;
import com.cm.utils.pathUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �������˵�ͼƬ����Ƶ�Լ����ͼƬ����Ƶ
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
public class checkPictureAction extends ActionSupport implements ModelDriven<PictureCheck> {

	@Autowired
	private IResourceService resourceService ;
	@Autowired
	private IUserService userService ;
	
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	private PictureCheck pictureCheck = new PictureCheck() ;
	private String returndata ;
	private File upload ;
	private String uploadFileName ;
	private List<PictureCheck> pictureChecks ;
	private static int MAXRESULTS = 10  ;
	private int currentPage ;
	private int toPage ;
	
	@Override
	public PictureCheck getModel() {
		return pictureCheck;
	}


	public String getReturndata() {
		return returndata;
	}


	public void setReturndata(String returndata) {
		this.returndata = returndata;
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
	
	
	

	//---------------------------------�û��ύ���ȴ����------------------------------------------------
	
	public int getToPage() {
		return toPage;
	}


	public void setToPage(int toPage) {
		this.toPage = toPage;
	}


	public List<PictureCheck> getPictureChecks() {
		return pictureChecks;
	}


	public void setPictureChecks(List<PictureCheck> pictureChecks) {
		this.pictureChecks = pictureChecks;
	}


	@Action(value="picToCheck",results= {
			@Result(name="success",location= "/UI2/addSuccess.html")
			
	})
	public String addCheck() {
		//��Ҫ����Ϣ��ͼƬ���ƣ�uploadFileName��ͼƬ�ļ���upload��ͼƬ�洢�ļ���λ�ã�filePath
		//���浽���ݱ����Ϣ��uri,name,com,tag,user_id
		//ͼƬ�洢�ļ���λ��
		String filePath =  pathUtils.picturePath() ;
		System.out.println(filePath);
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		System.out.println("�ļ�·��-�ļ���-�ļ�:"+filePath+"-"+uploadFileName+"-"+upload);
		
		
		pictureCheck.setCheckCom("�ȴ����");
		//y��������ˣ�n��δ��ˣ�f����˲�ͨ��
		pictureCheck.setCheckTag(0);
		pictureCheck.setPicName(uploadFileName);
		pictureCheck.setPicUri(filePath+"/"+uploadFileName);
		pictureCheck.setUserId(user.getUserId());
		
		System.out.println(pictureCheck);
		//���浽��
		resourceService.savePictureCheck(pictureCheck) ;
		
		
		//���浽����
		try {
			FileUtils.copyFile(upload, new File(file,uploadFileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("���浽�����ļ���ʧ��");
			return "fail" ;
		}
		
		
		
		
		//Ҫ�ĳɷ���һ��ҳ�棬������json
		return SUCCESS ;
	}
	//---------------------------------����Ա��ˣ��ⲿ����jsp------------------------------------------------
	
	//��ʾ�������Դ�б�
	
	@Action(value="toPcList",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
	})
	public String showCheckList() {
		
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		//����δ���
		pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
		//����ҳ��

		Long totalItems = resourceService.findAllPcs() ;
		Long totalPcs ;
				
		//��ҳ��
		if(0 == totalItems) {
			totalPcs = new Long(1);
		}else if(0 == totalItems%10) {
			totalPcs = totalItems/10 ;
		}else {
			totalPcs = (totalItems/10) + 1  ;
		}
	
		request.getSession().setAttribute("totalPcs",totalPcs );
		
		return SUCCESS;
	}
	
	
	//��˲鿴ͼƬ����
	@Action(value="pcDetail",results= {
			@Result(name="success",location= "/WEB-INF/jsp/management/resource/pcDetail.jsp")
	})
	public String pcDetail() {
		System.out.println(pictureCheck);
		
		PictureCheck pictureCheckTemp = resourceService.findPCById(pictureCheck.getPicId()) ;
		pictureCheck.setCheckCom(pictureCheckTemp.getCheckCom());
		pictureCheck.setPicName(pictureCheckTemp.getPicName());
		pictureCheck.setPicUri(pictureCheckTemp.getPicUri());
		pictureCheck.setResCom(pictureCheckTemp.getResCom());
		pictureCheck.setCheckTag(pictureCheckTemp.getCheckTag());
		pictureCheck.setUserId(pictureCheckTemp.getUserId());
		System.out.println("2"+pictureCheck);
		return SUCCESS ;
	}
	//���ͼƬ
	@Action(value="checkPic",results= {
			@Result(name="success",type="chain",location="toPcList")
	})
	public String checkedPicture() {
				
		System.out.println(pictureCheck);
		
		
		//��˲�ͨ��
		if(pictureCheck.getCheckTag() == -1) {
			resourceService.updatePictureCheck(pictureCheck);
			System.err.println("��˲�ͨ��");
			return SUCCESS ;
		}
		System.out.println("���ͨ��");
		
		Picture picture = new Picture() ;
		Resource resource = new Resource() ;
		
		
		picture.setPicName(pictureCheck.getPicName());
		picture.setPicUri(pictureCheck.getPicUri());
		resource.setPicture(picture);
		resource.setPubTime(WebUtils.getTime());
		resource.setResCom(pictureCheck.getResCom());
		resource.setUser(userService.findUserById(pictureCheck.getUserId()));
		resource.setResTag("pic");
		resource.setAdsId(user.getUserId());
		resourceService.saveResource(resource);
		resourceService.updatePictureCheck(pictureCheck);
		
		
		return SUCCESS ;
	}
	
	//��һҳ
		@Action(value="prePcPage",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
				
		})
		public String previousMcPage() {
			currentPage = (int) request.getSession().getAttribute("currentPage") ;
			currentPage = currentPage - 1 ;
			if(currentPage <= 0) {
				return "fail" ;
			}
			
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		//��һҳ
		@Action(value="nextPcPage",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
				
		})
		public String nextPcPage() {
			currentPage = (int) request.getSession().getAttribute("currentPage") ;
			System.out.println("currentpage:"+currentPage);
			currentPage = currentPage + 1 ;
			
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			System.out.println(pictureChecks.size());
			if(0 == pictureChecks.size()) {
				return "fail" ;
			}
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		//ǰ��ĳҳ
		@Action(value="selectPcPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")})
		public String selectPage() {
			
			System.out.println(toPage);
			currentPage = toPage ;
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			if(0 == pictureChecks.size()) {
				return "fail" ;
			}
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
}
