package com.cis2237.galczakp4.galczake4.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();



    static {
        addItem(new DummyItem("1", "Jax", "Top-Lane Hybrid Bruiser",
                "http://www.mobafire.com/league-of-legends/champion/jax-15"));
        addItem(new DummyItem("2", "Katarina", "Mid-Lane Mage",
                "http://www.mobafire.com/league-of-legends/champion/katarina-36"));
        addItem(new DummyItem("3", "Volibear", "Jungle Tank/Bruiser",
                "http://www.mobafire.com/league-of-legends/champion/volibear-88"));
        addItem(new DummyItem("4", "Character Details"));
        addItem(new DummyItem("5", "Character Images"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String name;
        public final String details;
        public final String website_url;

        public DummyItem(String id, String name, String details, String website_url) {
            this.id = id;
            this.name = name;
            this.details = details;
            this.website_url = website_url;
        }

        public DummyItem(String id, String name){
            this.id = id;
            this.name = name;
            details = "";
            website_url = "";
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
