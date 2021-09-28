package com.yangdi.leetcode.binarytree;

import java.util.*;

public class FileSystem {

    class FileNode {
        String name;
        List<FileNode> childrenNodes;
        List<String> files;

        public FileNode(String value) {
            this.name = value;
        }
    }

    FileNode root;
    Map<String, String> fileContent;

    public FileSystem() {
        root = new FileNode("");
        fileContent = new HashMap<>();
    }

    public List<String> ls(String path) {
        String[] paths = {""};
        if (!"/".equals(path)) {
            paths = path.split("/");
        }

        List<String> result = new ArrayList<>();
        FileNode preNode = root;

        for (int i = 1; i < paths.length; i++) {
            List<FileNode> kids = preNode.childrenNodes;
            if (kids == null) {
                break;
            }

            for (FileNode kid : kids) {
                if (paths[i].equals(kid.name)) {
                    preNode = kid;
                }
            }
        }

        if (preNode.childrenNodes != null && preNode.childrenNodes.size() != 0) { // directory names
            for (FileNode kid : preNode.childrenNodes) {
                result.add(kid.name);
            }
        }
        if (preNode.files != null && preNode.files.size() != 0) {
            for (String file : preNode.files) { // file names
                result.add(file);
            }
        }

        Collections.sort(result);
        return result;
    }

    public void mkdir(String path) {
        if ("/".equals(path)) {
            return;
        }

        FileNode preNode = root;
        String[] paths = path.split("/");

        for (int i = 1; i < paths.length; i++) {
            if (preNode.childrenNodes == null) {
                List<FileNode> kids = new ArrayList<>();
                FileNode kid = new FileNode(paths[i]);
                kids.add(kid);
                preNode.childrenNodes = kids;
                preNode = kid;
            } else {
                List<FileNode> kids = preNode.childrenNodes;
                boolean exist = false;

                for (FileNode kid : kids) {
                    if (paths[i].equals(kid.name)) { // path[i] exists
                        preNode = kid;
                        exist = true;
                        break;
                    }
                }

                if (!exist) { // path[i] doesn't exist
                    FileNode kid = new FileNode(paths[i]);
                    kids.add(kid);
                    preNode = kid;
                }
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        if (fileContent.containsKey(filePath)) {
            fileContent.put(filePath, fileContent.get(filePath) + content);
        } else {
            fileContent.put(filePath, content);

            // creat tree
            int index = filePath.lastIndexOf("/");
            //String directory = filePath.substring(0, index);
            String fileName = filePath.substring(index + 1);

            mkdir(filePath);
            FileNode leafNode = searchNode(filePath);
            if (leafNode.files == null) {
                List<String> files = new ArrayList();
                files.add(fileName);
                leafNode.files = files;
            } else {
                List<String> files = leafNode.files;
                files.add(fileName);
            }
        }
    }

    public String readContentFromFile(String filePath) {
        return fileContent.containsKey(filePath) ? fileContent.get(filePath) : "";
    }

    public FileNode searchNode(String filePath) {
        FileNode preNode = root;
        String[] paths = filePath.split("/");

        for (int i = 1; i < paths.length; i++) {
            boolean exist = false;
            List<FileNode> kids = preNode.childrenNodes;

            for (int j = 0 ; j < kids.size(); j++) {
                FileNode kid = kids.get(j);
                if (paths[i].equals(kid.name)) { // path[i] exists
                    preNode = kid;
                    exist = true;
                    break;
                }
            }

            if (!exist) { // path[i] doesn't exist
                return null;
            }
        }

        return preNode;
    }

    public static void main(String[] args) {
        // ["FileSystem", "mkdir", "mkdir", "ls", "ls"]
        // [[], ["/dig/x/etqm"], ["/jxk/x/uov"], ["/dig"], ["/dig/x"]]

        //["FileSystem","mkdir","ls","ls","mkdir","ls","ls","addContentToFile","readContentFromFile",
        // "ls","readContentFromFile"]
        //[[],["/zijzllb"],["/"],["/zijzllb"],["/r"],["/"],["/r"],["/zijzllb/hfktg","d"],["/zijzllb/hfktg"],
        // ["/"],["/zijzllb/hfktg"]]

        FileSystem obj = new FileSystem();
        obj.mkdir("/zijzllb");
        System.out.println(obj.ls("/"));
        System.out.println(obj.ls("/zijzllb"));
        obj.mkdir("/r");
        System.out.println(obj.ls("/"));
        System.out.println(obj.ls("/r"));
        obj.addContentToFile("/zijzllb/hfktg","d");
        System.out.println(obj.readContentFromFile("/zijzllb/hfktg"));
        System.out.println(obj.ls("/"));
        System.out.println(obj.ls("/zijzllb/hfktg"));
    }
}
