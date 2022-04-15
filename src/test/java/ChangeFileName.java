import java.io.File;

public class ChangeFileName {
    public static void main(String[] args) {
        String path = "C:\\Users\\Pangpd\\Desktop\\检测单";
        changeFileName(path);
    }

    public static void changeFileName(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        changeFileName(file.getAbsolutePath());
                    } else {
                        //是文件，判断是否需要重命名
                        File parentPath = new File("");//文件所在父级路径
                        parentPath = file.getParentFile();
                        String fileName = file.getName(); //旧名
                        // 获取文件名前半部分（车牌号）
                        String firstName = fileName.substring(0, fileName.indexOf("("));
                        // 获取文件名前后部分（车牌号）
                        String sedName = fileName.substring(fileName.indexOf("(") + 1, fileName.indexOf(")"));
                        //重新定义名字
                        String newName = sedName + "(" + firstName + ")" + ".docx";
                        File newFile = new File(parentPath + "/", newName);
                        System.out.println(fileName + "-->" + newName);
                        //进行重命名操作
                        if (file.renameTo(newFile)) {
                            System.out.println(file.getName() + "重命名成功---");
                        } else {
                            System.out.println(file.getName() + "重命名失败+++");
                        }
                    }
                }
            }
        }
    }
}