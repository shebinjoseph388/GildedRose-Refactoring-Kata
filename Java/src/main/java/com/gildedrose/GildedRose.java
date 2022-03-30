package com.gildedrose;

class GildedRose {
    public static final String PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        int bound = items.length;
        for (int i = 0; i < bound; i++) {
            updateSingleItem(i);
        }
    }

    private void updateSingleItem(int i) {
        updateQuality(i);

        updateSellIn(i);

        updateExpired(i);
    }

    private void updateQuality(int i) {
        if (items[i].name.equals(AGED_BRIE)
            || items[i].name.equals(PASSES)) {
            if (items[i].quality < 50) {
                incQuality(i);

                if (items[i].name.equals(PASSES)) {
                    passesUpdate(i);
                }
            }
        } else {
            if (items[i].quality > 0) {
                if (items[i].name.equals(RAGNAROS)) {
                } else {
                    decQuality(i);
                }
            }
        }
    }

    private void passesUpdate(int i) {
        if (items[i].sellIn < 11) {
            qualityCheck(i);
        }

        if (items[i].sellIn < 6) {
            qualityCheck(i);
        }
    }

    private void updateExpired(int i) {
        if (items[i].sellIn < 0) {
            if (items[i].name.equals(AGED_BRIE)) {
                qualityCheck(i);
            } else if (items[i].name.equals(PASSES)) {
                items[i].quality = items[i].quality - items[i].quality;
            } else if (items[i].quality > 0) {
                decQuality(i);
            }
        }
    }

    private void updateSellIn(int i) {
        if (!items[i].name.equals(RAGNAROS)) {
            decSellIn(i);
        }
    }

    private void decSellIn(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void qualityCheck(int i) {
        if (items[i].quality < 50) {
            incQuality(i);
        }
    }

    private void incQuality(int i) {
        items[i].quality = items[i].quality + 1;
    }

    private void decQuality(int i) {
        items[i].quality = items[i].quality - 1;
    }
}
