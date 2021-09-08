package com.nowcoder.community.dao;

import org.springframework.scheduling.support.SimpleTriggerContext;

import java.math.BigInteger;

public class Test {

    public static void main(String[] args) {

        String s = "I am a boy";
        String[] s1 = s.split(" ");
        System.out.println(s1[0]);
    }


//    private boolean dfs(char[][] board, boolean[][] visited, int i, int j, int index, char[] chs) {
//        if (index == chs.length) return true;
//        visited[i][j] = true;
//        // inflect with four dircetions
//        if (i-1 > 0 && !visited[i-1][j] && board[i-1][j]==chs[index])
//            return dfs(board, visited, i-1, j, index+1, chs);
//        if (j-1 > 0 && !visited[i][j-1] && board[i][j-1]==chs[index])
//            return dfs(board, visited, i, j-1, index+1, chs);
//        if (i+1 < M && !visited[i+1][j] && board[i+1][j]==chs[index])
//            return dfs(board, visited, i+1, j, index+1, chs);
//        if (j+1 < N && !visited[i][j+1] && board[i][j+1]==chs[index])
//            return dfs(board, visited, i, j+1, index+1, chs);
//
//        return false;
//    }

}
