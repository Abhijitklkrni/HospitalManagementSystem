package org.test.hospitalmanagementsystem.ProblemSolving;

class Side {

    int from;
    int to;
    int sideLength;

    public Side(int x, int y, int sideLength) {
        this.from = x;
        this.to = y;
        this.sideLength = sideLength;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String toString() {
        return "Side{" +
                "from=" + from +
                ", to=" + to +
                ", sideLength=" + sideLength +
                '}';
    }
}
