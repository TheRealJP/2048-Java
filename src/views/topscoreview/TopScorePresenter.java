package views.topscoreview;

import model.TopScores;

public class TopScorePresenter {

    private TopScores model;
    private TopScoreView view;

    public TopScorePresenter(TopScores model, TopScoreView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateLayout();
    }

    private void addEventHandlers() {


    }

    private void updateLayout() {

        view.setSpelers(model.getSpelerLijst());
    }
}
