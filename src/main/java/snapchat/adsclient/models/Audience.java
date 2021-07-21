package snapchat.adsclient.models;

public class Audience {
    String name;
    String status;
    String type;
    String placement;
    String bid_micro;
    String auto_bid;
    String daily_budget_micro;
    String delivery_constraint;
    String optimization_goal;
    String[] included_content_types;
    Targeting targeting;

    public Audience() {
    }

    public Audience(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getBid_micro() {
        return bid_micro;
    }

    public void setBid_micro(String bid_micro) {
        this.bid_micro = bid_micro;
    }

    public String getAuto_bid() {
        return auto_bid;
    }

    public void setAuto_bid(String auto_bid) {
        this.auto_bid = auto_bid;
    }

    public String getDaily_budget_micro() {
        return daily_budget_micro;
    }

    public void setDaily_budget_micro(String daily_budget_micro) {
        this.daily_budget_micro = daily_budget_micro;
    }

    public String getDelivery_constraint() {
        return delivery_constraint;
    }

    public void setDelivery_constraint(String delivery_constraint) {
        this.delivery_constraint = delivery_constraint;
    }

    public String getOptimization_goal() {
        return optimization_goal;
    }

    public void setOptimization_goal(String optimization_goal) {
        this.optimization_goal = optimization_goal;
    }

    public String[] getIncluded_content_types() {
        return included_content_types;
    }

    public void setIncluded_content_types(String[] included_content_types) {
        this.included_content_types = included_content_types;
    }

    public Targeting getTargeting() {
        return targeting;
    }

    public void setTargeting(Targeting targeting) {
        this.targeting = targeting;
    }
}
