package com.mostafa.root.ismartgp.Model;

public class ProblemModel {

        private String from , problem ;

        public ProblemModel() {
        }

        public ProblemModel(String from , String problem) {
            this.from = from;
            this.problem = problem;
        }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
