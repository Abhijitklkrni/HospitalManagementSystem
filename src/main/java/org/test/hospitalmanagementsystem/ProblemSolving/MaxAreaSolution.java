package org.test.hospitalmanagementsystem.ProblemSolving;

import java.util.*;
import java.util.stream.Collectors;

public class MaxAreaSolution {
    /*
        N : Number of horizontal fences
        M : Number of vertical fences
        Overall TC:  O(N^2 + M^2)
        Overall SC:  O(N^2 + M^2)
    */
    public static void main(String[] args) {
        int[] hFences = {2,3};
        int[] vFences = {2};
        int m = 4;
        int n = 3;
        System.out.println("MAX SQUARE AREA: "+new MaxAreaSolution().maxSquareArea(m,n,hFences, vFences));
    }

    public int maxSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int mod = (int)1e9 + 7;
        List<Side> hSides = getPossibleSides(hFences, m); // O(N^2) SC: O(N^2) (it follows (n-1) + (n-2) + (n-3) + ... + 1, is a series that sums up to n*(n-1)/2. So, the complexity is O(n^2))
        List<Side>  vSides = getPossibleSides(vFences, n);// O(M^2) SC: O(M^2) (it follows (n-1) + (n-2) + (n-3) + ... + 1, is a series that sums up to n*(n-1)/2. So, the complexity is O(n^2))
        Map<SideKey, Side> vSideMap = vSides.stream()
                .collect(Collectors.toMap(
                        side -> new SideKey(side.getFrom(), side.getSideLength()),
                        side -> side));
        long maxArea = 0;
        for (Side value : hSides) {
            Side vSide =  vSideMap.get(new SideKey(value.getFrom(), value.getSideLength()));
            Side hSide = value;
            if(vSide == null) continue;
            long area = (long) hSide.getSideLength() * hSide.getSideLength();
            maxArea = Math.max(maxArea, area);
        }
        return maxArea == 0 ? -1 : (int)(maxArea % mod);
    }


    public static List<Side> getPossibleSides(int[] fences, int end) {
        Arrays.sort(fences);
        //convert fences to list
        List<Integer> fencesList = new ArrayList<>();
        fencesList.add(1);
        for (int fence : fences) {
            fencesList.add(fence);
        }
        fencesList.add(end);

        List<Side> totalFences = new ArrayList<>();
        for (int i = 0; i < fencesList.size() - 1; i++) {
            for(int j = i + 1; j < fencesList.size(); j++) {
                int from = fencesList.get(i);
                int to = fencesList.get(j);
                totalFences.add(new Side(from,to,Math.abs(from - to)));
            }
        }
        return totalFences;
    }
}


