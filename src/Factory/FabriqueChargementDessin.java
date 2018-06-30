package Factory;

import modele.ChargementV1;
import modele.ChargementV2;
import modele.Dessin;

public class FabriqueChargementDessin {
    private static FabriqueChargementDessin instance = new FabriqueChargementDessin();

    private static FabriqueChargementDessin getInstance() {
        return instance;
    }

    public ChargementV1 getChargementV1(Dessin dessin) {
        return new ChargementV1(dessin);
    }

    public ChargementV2 getChargementV2(Dessin dessin) {
        return new ChargementV2(dessin);
    }
}
