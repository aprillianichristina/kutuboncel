import java.io.*;
import java.net.*;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";
    private static final String MAVEN_WRAPPER_PATH = ".mvn/wrapper/maven-wrapper.jar";
    private static final String MAVEN_WRAPPER_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
            + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    public static void main(String args[]) {
        File baseDirectory = new File(args[0]);
        System.out.println("Maven Wrapper - Downloading...");

   
        if (isMavenWrapperAlreadyDownloaded(baseDirectory)) {
            System.out.println("Maven Wrapper - Already downloaded");
            return;
        }

        try {
            downloadMavenWrapper(baseDirectory);
            System.out.println("Maven Wrapper - Downloaded successfully");
        } catch (Exception e) {
            System.err.println("Maven Wrapper - Error downloading: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isMavenWrapperAlreadyDownloaded(File baseDirectory) {
        File mavenWrapperJar = new File(baseDirectory, MAVEN_WRAPPER_PATH);
        return mavenWrapperJar.exists();
    }

    private static void downloadMavenWrapper(File baseDirectory) throws Exception {
        File mavenWrapperDirectory = new File(baseDirectory, ".mvn/wrapper");
        if (!mavenWrapperDirectory.exists()) {
            mavenWrapperDirectory.mkdirs();
        }

        File mavenWrapperJar = new File(mavenWrapperDirectory, "maven-wrapper.jar");
        if (mavenWrapperJar.exists()) {
            return;
        }

        URL mavenWrapperUrl = new URL(MAVEN_WRAPPER_URL);
        try (InputStream in = mavenWrapperUrl.openStream();
                OutputStream out = new FileOutputStream(mavenWrapperJar)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}

