package org.example.construct.BTree;
import java.io.*;
import java.util.*;

/**
 * @className: HufmanTree
 * @description:
 * @author: rainple
 * @create: 2021-08-10 21:11
 **/
public class HuffmanTree {

    private static final Map<String,Map<String,Byte>> CODE_TABLE = new HashMap<>();

    public void zip(byte[] bytes,String dest) {
        List<HuffmanNode> nodes = getNodes(bytes);
        HuffmanNode tree = createTree(nodes);
        String encode = encode(tree,bytes,"test");
        //String decode = decode(encode);
        OutputStream outputStream = null;
        try {
            File file = new File(dest);
            outputStream = new FileOutputStream(file);
            outputStream.write(encode.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void zip(String destFile,String srcFile) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(srcFile));
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,bytes.length);
            zip(bytes,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<HuffmanNode> nodes = getNodes(destFile.getBytes());
    }

    private HuffmanNode createTree(List<HuffmanNode> nodes) {
        Queue<HuffmanNode> queue1 = new LinkedList<>(nodes);
        Queue<HuffmanNode> queue2 = new ArrayDeque<>();
        HuffmanNode root = null;
        while (!queue1.isEmpty()) {
            HuffmanNode node = queue1.poll();
            HuffmanNode peek1 = queue1.peek();
            HuffmanNode peek2 = queue2.peek();
            HuffmanNode p = new HuffmanNode(null, null);
            if (peek1 != null && peek2 != null) {
                if (peek1.weight < peek2.weight) {
                    p.weight = node.weight + peek1.weight;
                    p.left = peek1;
                    p.right = node;
                    queue1.poll();
                } else {
                    p.left = peek2;
                    p.right = node;
                    p.weight = node.weight + peek2.weight;
                    queue2.poll();
                }
                queue2.offer(p);
            } else if (peek2 != null) {
                p.weight = node.weight + peek2.weight;
                if (node.weight > peek2.weight) {
                    p.left = peek2;
                    p.right = node;
                } else {
                    p.left = node;
                    p.right = peek2;
                }
                queue2.poll();
                queue2.offer(p);
            } else if (peek1 != null) {
                p.weight = node.weight + peek1.weight;
                p.left = node;
                p.right = peek1;
                queue1.poll();
                queue2.offer(p);
            }
            root = p;
        }
        List<HuffmanNode> nodeList = new ArrayList<>();
        while (!queue2.isEmpty()) {
            nodeList.add(queue2.poll());
        }
        Collections.sort(nodeList);
        while (!nodeList.isEmpty()) {
            HuffmanNode huffmanNode = nodeList.get(0);
            HuffmanNode huffmanNode1 = nodeList.get(1);
            HuffmanNode p = new HuffmanNode(huffmanNode.weight + huffmanNode1.weight, null);
            p.left = huffmanNode;
            p.right = huffmanNode1;
            nodeList.remove(0);
            nodeList.remove(0);
            root = p;
        }
        if (root != null) {
            System.out.println("当前权重：" + root.weight);
        }else {
            System.out.println("空树");
        }
        return root;
    }

    private String encode(HuffmanNode node,byte[] code,String tableName) {
        if (node == null || code.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Map<Byte,String> map = new HashMap<>();
        Map<String,Byte> table = new HashMap<>();
        getCode(node,"",map,table);
        for (byte c : code) {
            builder.append(map.get(c));
        }
        CODE_TABLE.put(tableName,table);
        return builder.toString();
    }

    private String decode(String code) {
        Map<String, Byte> map = CODE_TABLE.get("test");
        if (map == null) {
            System.out.println("解码有误");
            return "";
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = code.toCharArray();
        int start = 0,end = 1;
        while (end <= chars.length) {
            String m = code.substring(start, end);
            Byte b = map.get(m);
            if (b != null) {
                builder.append((char)b.intValue());
                start = end;
            }
            end++;
        }
        return builder.toString();
    }

    private void getCode(HuffmanNode node, String code, Map<Byte, String> map, Map<String, Byte> table) {
        if (node.left == null && node.right == null) {
            map.put(node.data,code);
            table.put(code,node.data);
        }
        if (node.left != null) {
            getCode(node.left,code + "0", map, table);
        }
        if (node.right != null) {
            getCode(node.right,code + "1", map, table);
        }
    }

    private List<HuffmanNode> getNodes(byte[] bytes) {
        Map<Byte,HuffmanNode> map = new HashMap<>();
        for (int i = 0; i < bytes.length; i++) {
            HuffmanNode huffmanNode = map.get(bytes[i]);
            if (huffmanNode == null) {
                map.put(bytes[i],new HuffmanNode(1,bytes[i]));
            } else {
                Integer weight = huffmanNode.weight;
                huffmanNode.weight = weight + 1;
            }
        }
        List<HuffmanNode> nodes = new ArrayList<>(map.values());
        Collections.sort(nodes);
        return nodes;
    }

    public void unzip(String destFile,String srcFile) {

    }

    private static class HuffmanNode implements Comparable<HuffmanNode> {

        private Integer weight;
        private Byte data;
        private HuffmanNode left;
        private HuffmanNode right;

        public HuffmanNode(Integer weight, Byte data, HuffmanNode left, HuffmanNode right) {
            this.weight = weight;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public HuffmanNode(Integer weight, Byte data) {
            this.weight = weight;
            this.data = data;
        }

        @Override
        public int compareTo(HuffmanNode node) {
            return this.weight - node.weight;
        }

        @Override
        public String toString() {
            return "HuffmanNode{" +
                    "weight=" + weight +
                    ", data=" + data +
                    '}';
        }
    }

    private static class CodeTable {
        String code;
        char data;

        public CodeTable(String code, char data) {
            this.code = code;
            this.data = data;
        }
    }

}
