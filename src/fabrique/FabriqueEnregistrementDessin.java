package fabrique;

import modele.EnregistrementV1;
import modele.EnregistrementV2;

public class FabriqueEnregistrementDessin {
    private static FabriqueEnregistrementDessin instance = new FabriqueEnregistrementDessin();

    private static FabriqueEnregistrementDessin getInstance() {
        return instance;
    }

    public EnregistrementV1 getEnregistrementV1() {
        return new EnregistrementV1();
    }

    public EnregistrementV2 getEnregistrementV2() {
        return new EnregistrementV2();
    }
}
