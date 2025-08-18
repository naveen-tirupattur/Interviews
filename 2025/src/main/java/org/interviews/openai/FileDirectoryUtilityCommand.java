package org.interviews.openai;

import java.util.*;

public class FileDirectoryUtilityCommand {
  public static String cd(String pwd, String inputPath, Map<String, String> symLinkMap) {
    Deque<String> directories = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    // TODO - What is $HOME?
    String home = "/Users/x";
    if (inputPath.equals("~")) {
      inputPath = home;
    } else if (inputPath.startsWith("~/")) {
      inputPath = home + inputPath.substring(1);
    }

    if (inputPath.startsWith("/")) {

    } else {
      for (String currentDir : pwd.split("/")) {
        if (!currentDir.isEmpty()) directories.addLast(currentDir);
      }
    }

    for (String inputDir : inputPath.split("/")) {
      if (inputDir.isEmpty() || inputDir.equals(".")) continue;
      if (inputDir.equals("..")) {
        if (!directories.isEmpty()) directories.removeLast();
      } else {
        directories.addLast(inputDir);
        while (true) {
          String pathSoFar = "/" + String.join("/", directories);
          if (symLinkMap.containsKey(pathSoFar)) {
            if (visited.contains(pathSoFar)) throw new IllegalArgumentException("Symlink loop detected: " + pathSoFar);
            visited.add(pathSoFar);
            String symLink = symLinkMap.get(pathSoFar);
            Deque<String> newDequeue = new ArrayDeque<>();
            // Absolute Path
            if (symLink.startsWith("/")) {
              for (String symLinkDir : symLink.split("/")) {
                if (symLinkDir.isEmpty() || symLinkDir.equals(".")) continue;
                if (symLinkDir.equals("..")) {
                  if (!newDequeue.isEmpty()) newDequeue.removeLast();
                } else {
                  newDequeue.add(symLinkDir);
                }
              }
            } else {
              // Relative path
              // Go to the parent
              String parentAbs = pathSoFar.substring(0, pathSoFar.lastIndexOf("/"));
              if (parentAbs.isEmpty()) parentAbs = "/";
              parentAbs = parentAbs + "/" + symLink;
              for (String symLinkDir : symLink.split("/")) {
                if (symLinkDir.isEmpty() || symLinkDir.equals(".")) continue;
                if (symLinkDir.equals("..")) {
                  if (!newDequeue.isEmpty()) newDequeue.removeLast();
                } else {
                  newDequeue.add(symLinkDir);
                }
              }
            }
            directories.clear();
            directories.addAll(newDequeue);
            continue; // check again incase the updated directories stack has a new symlink
          }
          break;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (String dir : directories) {
      sb.append("/").append(dir);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // --- Basic Path Navigation Tests ---
    String pwd = "/Users/x/documents";
    Map<String, String> symLinkMap = new HashMap<>();

    System.out.println("--- Basic Path Navigation ---");
    System.out.println("cd(\"/Users/x/documents\", \"/var/www\") -> " + cd(pwd, "/var/www", symLinkMap)); // Absolute path
    System.out.println("cd(\"/Users/x/documents\", \"project\") -> " + cd(pwd, "project", symLinkMap)); // Relative path
    System.out.println("cd(\"/Users/x/documents\", \"..\") -> " + cd(pwd, "..", symLinkMap)); // Parent directory
    System.out.println("cd(\"/Users/x/documents\", \"../../etc\") -> " + cd(pwd, "../../etc", symLinkMap)); // Multiple parent directories
    System.out.println("cd(\"/Users/x/documents\", \"./photos\") -> " + cd(pwd, "./photos", symLinkMap)); // Current directory
    System.out.println("cd(\"/Users/x/documents\", \"~/\") -> " + cd(pwd, "~/", symLinkMap)); // Home directory shortcut
    System.out.println("cd(\"/Users/x/documents\", \"~/naveen\") -> " + cd(pwd, "~/naveen", symLinkMap)); // Home directory with sub-path
    System.out.println("cd(\"/Users/x/documents\", \"\") -> " + cd(pwd, "", symLinkMap)); // Empty path input

    System.out.println("\n--- Symbolic Link Tests ---");
    // Define symbolic links
    symLinkMap.put("/Users/x/documents/project_link", "/var/www");
    symLinkMap.put("/Users/x/documents/photos_link", "photos/vacation");
    symLinkMap.put("/Users/x/dev/server_link", "../var/www");
    symLinkMap.put("/home/user/my_app", "/var/www/html");

    // Test cases with symbolic links
    System.out.println("cd(\"/Users/x/documents\", \"project_link\") -> " + cd(pwd, "project_link", symLinkMap)); // Relative symlink to absolute path
    System.out.println("cd(\"/Users/x/documents\", \"./photos_link\") -> " + cd(pwd, "./photos_link", symLinkMap)); // Relative symlink to relative path
    System.out.println("cd(\"/Users/x/dev\", \"server_link\") -> " + cd("/Users/x/dev", "server_link", symLinkMap)); // Relative symlink from a different PWD
    System.out.println("cd(\"/\", \"home/user/my_app\") -> " + cd("/", "home/user/my_app", symLinkMap)); // Absolute symlink from root

    // --- Edge Cases and Error Handling ---
    System.out.println("\n--- Edge Cases ---");
    System.out.println("cd(\"/\", \"..\") -> " + cd("/", "..", symLinkMap)); // Navigating up from root
    System.out.println("cd(\"/a/b/c\", \"/a/./b/../c\") -> " + cd("/a/b/c", "/a/./b/../c", symLinkMap)); // Path with . and ..
    System.out.println("cd(\"/a/b\", \"../../c\") -> " + cd("/a/b", "../../c", symLinkMap)); // Navigating up past root

    System.out.println("\n--- Symlink Loop Test (Expected Error) ---");
    // This should throw an IllegalArgumentException
    Map<String, String> loopSymLinkMap = new HashMap<>();
    loopSymLinkMap.put("/a/b", "/a/c");
    loopSymLinkMap.put("/a/c", "/a/b");
    try {
      cd("/a", "b", loopSymLinkMap);
    } catch (IllegalArgumentException e) {
      System.out.println("Caught expected exception: " + e.getMessage());
    }
  }
}
