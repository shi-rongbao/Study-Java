package com.study.zone;

import java.time.ZoneId;
import java.util.Set;

public class ZoneIdDemo1 {
    public static void main(String[] args) {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds);
        System.out.println(zoneIds.size());

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);

        ZoneId zoneId1 = ZoneId.of("America/Cuiaba");
        System.out.println(zoneId1);



    }
}
