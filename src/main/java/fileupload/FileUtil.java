package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	public static String uploadFile(HttpServletRequest req, String sDirectory) {
		String originalFileName = "";
		try {
			//Part 객체를 통해 서버로 전송된 파일명 읽어오기 
			Part part = req.getPart("ofile");
			originalFileName = FileUtil.getFilename(part);
			if (!originalFileName.isEmpty()) {
				part.write(sDirectory+ File.separator +originalFileName);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return originalFileName;			
	}
	
	public static String getFilename(Part part) {
		//Part 객체의 헤더값 중 content-disposition 읽어오기 
        String partHeader = part.getHeader("content-disposition");
        //출력결과 : form-data; name="attachedFile"; filename="야옹.jpg"
        System.out.println("partHeader="+ partHeader);
        
        String[] phArr = partHeader.split("filename=");
        String fName = phArr[1].trim().replace("\"", "");
        System.out.println("원본 파일명:"+ fName);
        return fName;
    }
	
	public static String renameFile(String sDirectory, String fName) {
		String ext = fName.substring(fName.lastIndexOf("."));  // 파일 확장자
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;  // 새로운 파일 이름("업로드일시.확장자")

		File oldFile = new File(sDirectory + File.separator + fName);
	    File newFile = new File(sDirectory + File.separator + newFileName);
	    oldFile.renameTo(newFile);
	    System.out.println("변경된 파일명:"+ newFileName);
	    
	    return newFileName;
	}
	
    // 명시한 파일을 찾아 다운로드합니다.
    public static void download(HttpServletRequest req, HttpServletResponse resp,
            String directory, String sfileName, String ofileName) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        try {
            // 파일을 찾아 입력 스트림 생성
            File file = new File(sDirectory, sfileName);
            InputStream iStream = new FileInputStream(file);

            // 한글 파일명 깨짐 방지
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            else {
                ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
            }

            // 파일 다운로드용 응답 헤더 설정
            resp.reset();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition",
                           "attachment; filename=\"" + ofileName + "\"");
            resp.setHeader("Content-Length", "" + file.length() );

            //out.clear();  // 출력 스트림 초기화

            // response 내장 객체로부터 새로운 출력 스트림 생성
            OutputStream oStream = resp.getOutputStream();

            // 출력 스트림에 파일 내용 출력
            byte b[] = new byte[(int)file.length()];
            int readBuffer = 0;
            while ( (readBuffer = iStream.read(b)) > 0 ) {
                oStream.write(b, 0, readBuffer);
            }

            // 입/출력 스트림 닫음
            iStream.close();
            oStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("예외가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    // 지정한 위치의 파일을 삭제합니다.
    public static void deleteFile(HttpServletRequest req,
            String directory, String filename) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        File file = new File(sDirectory + File.separator + filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
