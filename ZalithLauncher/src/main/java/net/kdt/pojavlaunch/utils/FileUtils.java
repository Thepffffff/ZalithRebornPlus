/*
package net.kdt.pojavlaunch.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    */
/**
     * Check if a file denoted by a String path exists.
     * @param filePath the path to check
     * @return whether it exists (same as File.exists()
     *//*

    public static boolean exists(String filePath){
        return new File(filePath).exists();
    }

    */
/**
     * Get the file name from a path/URL string.
     * @param pathOrUrl the path or the URL of the file
     * @return the file's name
     *//*

    public static String getFileName(String pathOrUrl) {
        int lastSlashIndex = pathOrUrl.lastIndexOf('/');
        if(lastSlashIndex == -1) return null;
        return pathOrUrl.substring(lastSlashIndex);
    }

    */
/**
     * Remove the extension (all text after the last dot) from a path/URL string.
     * @param pathOrUrl the path or the URL of the file
     * @return the input with the extension removed
     *//*

    public static String removeExtension(String pathOrUrl) {
        int lastDotIndex = pathOrUrl.lastIndexOf('.');
        if(lastDotIndex == -1) return pathOrUrl;
        return pathOrUrl.substring(0, lastDotIndex);
    }

    */
/**
     * Ensure that a directory exists, is a directory and is writable.
     * @param targetFile the directory to check
     * @return if the check has succeeded
     *//*

    public static boolean ensureDirectorySilently(File targetFile) {
        if(targetFile.isFile()) return false;
        if(targetFile.exists()) return targetFile.canWrite();
        else return targetFile.mkdirs();

    }

    */
/**
     * Ensure that the parent directory of a file exists and is writable
     * @param targetFile the File whose parent should be checked
     * @return if the check as succeeded
     *//*

    public static boolean ensureParentDirectorySilently(File targetFile) {
        File parentFile = targetFile.getParentFile();
        if(parentFile == null) return false;
        return ensureDirectorySilently(parentFile);
    }

    */
/**
     * Same as ensureDirectorySilently(), but throws an IOException telling why the check failed.
     * @param targetFile the directory to check
     * @throws IOException when the checks fail
     *//*

    public static void ensureDirectory(File targetFile) throws IOException{
        if(targetFile.isFile()) throw new IOException("Target directory is a file");
        if(targetFile.exists()) {
            if(!targetFile.canWrite()) throw new IOException("Target directory is not writable");
        }else if(!targetFile.mkdirs()) throw new IOException("Unable to create target directory");
    }

    */
/**
     * Same as ensureParentDirectorySilently(), but throws an IOException telling why the check failed.
     * @param targetFile the File whose parent should be checked
     * @throws IOException when the checks fail
     *//*

    public static void ensureParentDirectory(File targetFile) throws IOException{
        File parentFile = targetFile.getParentFile();
        if(parentFile == null) throw new IOException("targetFile does not have a parent");
        ensureDirectory(parentFile);
    }
}*/
package net.kdt.pojavlaunch.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * Check whether a file denoted by a string path exists.
     *
     * @param filePath the path to check
     * @return true if the file exists
     */
    public static boolean exists(String filePath) {
        return filePath != null && new File(filePath).exists();
    }

    /**
     * Get the file name from a path or URL string.
     *
     * Example:
     * https://example.com/files/mod.jar -> mod.jar
     * /storage/emulated/0/test.txt -> test.txt
     *
     * @param pathOrUrl the path or URL of the file
     * @return the file name, or the original input if it has no slash, or null if the input is null/empty
     */
    public static String getFileName(String pathOrUrl) {
        if (pathOrUrl == null || pathOrUrl.isEmpty()) return null;

        int lastSlashIndex = pathOrUrl.lastIndexOf('/');
        if (lastSlashIndex == -1) return pathOrUrl;
        if (lastSlashIndex == pathOrUrl.length() - 1) return "";

        return pathOrUrl.substring(lastSlashIndex + 1);
    }

    /**
     * Remove the extension (all text after the last dot) from a path or URL string.
     *
     * @param pathOrUrl the path or URL of the file
     * @return the input with the extension removed
     */
    public static String removeExtension(String pathOrUrl) {
        if (pathOrUrl == null || pathOrUrl.isEmpty()) return pathOrUrl;

        int lastDotIndex = pathOrUrl.lastIndexOf('.');
        if (lastDotIndex == -1) return pathOrUrl;
        return pathOrUrl.substring(0, lastDotIndex);
    }

    /**
     * Ensure that a directory exists, is a directory, and is writable.
     *
     * @param targetFile the directory to check
     * @return true if the directory is ready to use
     */
    public static boolean ensureDirectorySilently(File targetFile) {
        if (targetFile == null) return false;
        if (targetFile.exists()) {
            return targetFile.isDirectory() && targetFile.canWrite();
        }
        return targetFile.mkdirs();
    }

    /**
     * Ensure that the parent directory of a file exists and is writable.
     *
     * @param targetFile the file whose parent should be checked
     * @return true if the parent directory is ready to use
     */
    public static boolean ensureParentDirectorySilently(File targetFile) {
        if (targetFile == null) return false;
        File parentFile = targetFile.getParentFile();
        if (parentFile == null) return false;
        return ensureDirectorySilently(parentFile);
    }

    /**
     * Same as ensureDirectorySilently(), but throws an IOException explaining why the check failed.
     *
     * @param targetFile the directory to check
     * @throws IOException when the checks fail
     */
    public static void ensureDirectory(File targetFile) throws IOException {
        if (targetFile == null) {
            throw new IOException("Target directory is null");
        }

        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                throw new IOException("Target directory is a file: " + targetFile.getAbsolutePath());
            }
            if (!targetFile.canWrite()) {
                throw new IOException("Target directory is not writable: " + targetFile.getAbsolutePath());
            }
            return;
        }

        if (!targetFile.mkdirs() && !targetFile.isDirectory()) {
            throw new IOException("Unable to create target directory: " + targetFile.getAbsolutePath());
        }
    }

    /**
     * Same as ensureParentDirectorySilently(), but throws an IOException explaining why the check failed.
     *
     * @param targetFile the file whose parent should be checked
     * @throws IOException when the checks fail
     */
    public static void ensureParentDirectory(File targetFile) throws IOException {
        if (targetFile == null) {
            throw new IOException("Target file is null");
        }

        File parentFile = targetFile.getParentFile();
        if (parentFile == null) {
            throw new IOException("Target file does not have a parent: " + targetFile.getAbsolutePath());
        }

        ensureDirectory(parentFile);
    }
}
