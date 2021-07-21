package snapchat.adsclient.models;

import snapchat.adsclient.models.targeting.Demographics;
import snapchat.adsclient.models.targeting.Geos;

public class Targeting {
    Geos[] geos;
    Demographics[] demographics;

    public Geos[] getGeos() {
        return geos;
    }

    public void setGeos(Geos[] geos) {
        this.geos = geos;
    }

    public Demographics[] getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics[] demographics) {
        this.demographics = demographics;
    }
}
