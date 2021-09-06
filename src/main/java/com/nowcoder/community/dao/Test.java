package com.nowcoder.community.dao;

import java.math.BigInteger;

public class Test {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'a', 'b'}};

        String word = "ba";
        System.out.println(board[0][0]);
        System.out.println(board[0][0] ^= 256);
        System.out.println(board[0][1]);
        System.out.println(board[0][ 1] ^= 256);
//[539834657,21,539834678,539834699,,,-1595793843,23710233,-1572083610,-1548373377]
//        BigInteger x = new BigInteger(1619504076 + 1079669377);
//        System.out.println(num);
        BigInteger b = new BigInteger("1232");
        BigInteger c = new BigInteger("1232");
        System.out.println(b.equals(c));
        int i = b.intValue();
        System.out.println(i);
    }

    private int M = 0;
    private int N = 0;
    public boolean exist(char[][] board, String word) {
        if (word == null) return false;
        char[] chs = word.toCharArray();
        M = board.length;
        N = board[0].length;
        for (int i = 0;i < M;i++)
            for (int j = 0;j < N;j++)
                if (board[i][j] == chs[0] &&
                        dfs(board, new boolean[M][N], i, j, 1, chs))
                    return true;

        return false;
    }


    private boolean dfs(char[][] board, boolean[][] visited, int i, int j, int index, char[] chs) {
        if (index == chs.length) return true;
        visited[i][j] = true;
        // inflect with four dircetions
        if (i-1 > 0 && !visited[i-1][j] && board[i-1][j]==chs[index])
            return dfs(board, visited, i-1, j, index+1, chs);
        if (j-1 > 0 && !visited[i][j-1] && board[i][j-1]==chs[index])
            return dfs(board, visited, i, j-1, index+1, chs);
        if (i+1 < M && !visited[i+1][j] && board[i+1][j]==chs[index])
            return dfs(board, visited, i+1, j, index+1, chs);
        if (j+1 < N && !visited[i][j+1] && board[i][j+1]==chs[index])
            return dfs(board, visited, i, j+1, index+1, chs);

        return false;
    }

}
