package com.example.gsadev.quiz.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSA Dev on 2/21/2018.
 */

public class QuizData {
    protected List<String> questions=new ArrayList<>();
    protected List<List<String>> options=new ArrayList<>();
    protected List<Integer> answers=new ArrayList<>();

    public void setQuestions() {
        questions.add("Grand Central Terminal, Park Avenue, New York is the world's?");
        questions.add("Entomology is the science that studies");
        questions.add("Eritrea, which became the 182nd member of the UN in 1993, is in the continent of");
        questions.add("Garampani sanctuary is located at");
        questions.add("Hitler party which came into power in 1933 is known as");
    }

    public void setOptions() {
        List<String> option1=new ArrayList<>();
        option1.add("largest railway station");
        option1.add("highest railway station");
        option1.add("longest railway station");

        List<String> option2=new ArrayList<>();
        option2.add("Behavior of human beings");
        option2.add("Insects");
        option2.add("The origin and history of technical and scientific terms");

        List<String> option3=new ArrayList<>();
        option3.add("Asia");
        option3.add("Africa");
        option3.add("Europe");


        List<String> option4=new ArrayList<>();
        option4.add("Junagarh, Gujarat");
        option4.add("Diphu, Assam");
        option4.add("Kohima, Nagaland");

        List<String> option5=new ArrayList<>();
        option5.add("Labour Party");
        option5.add("Ku-Klux-Klan");
        option5.add("Nazi Party");


        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
    }

    public void setAnswers() {
        answers.add(1);
        answers.add(2);
        answers.add(3);
        answers.add(2);
        answers.add(3);
    }

    public  List<String> getQuestions() {
        return questions;
    }

    public List<List<String>> getOptions() {
        return options;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
}
