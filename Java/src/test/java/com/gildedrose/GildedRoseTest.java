package com.gildedrose;

import org.junit.jupiter.api.Test;
//import org.junit.runners.Parameterized;
//import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    public void should_never_changes_quailty_of_Sulfuras() throws Exception {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void should_never_changes_sellIn_of_Sulfuras() throws Exception {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void should_lower_the_sellIn_by_one_for_normal_items() throws Exception {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void should_lower_the_quality_by_one_for_normal_items() throws Exception {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
    }

    @Test
    public void should_not_lower_the_quality_below_zero() throws Exception {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void should_lower_the_quality_twice_as_fast_once_the_sell_in_date_has_passed() throws Exception {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", -1, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    public void should_increase_the_quality_of_aged_brie_as_it_gets_older() throws Exception {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, app.items[0].quality);
    }

    @Test
    public void should_not_increase_the_quality_of_aged_brie_over_50() throws Exception {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void should_lower_backstage_passes_to_zero_quality_once_concert_has_happened() throws Exception {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void should_increase_backstage_passes_quality_by_1_when_the_concert_is_more_than_10_days_away() throws Exception {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    public void should_increase_backstage_passes_quality_by_2_when_the_concert_is_10_days_or_less_away() throws Exception {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(29, app.items[0].quality);
    }

    @Test
    public void should_increase_backstage_passes_quality_by_3_when_the_concert_is_5_days_or_less_away() throws Exception {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(47, app.items[0].quality);
    }

    @Test
    public void should_not_increase_backstage_passes_above_a_quality_of_50() throws Exception {
        Item[] backStagePassMoreThan10DaysAway = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50)};
        Item[] backStagePass10DaysAway = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)};
        Item[] backStagePass5DaysAway = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)};

        GildedRose app = new GildedRose(backStagePassMoreThan10DaysAway);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);

        GildedRose app1 = new GildedRose(backStagePass10DaysAway);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);

        GildedRose app2 = new GildedRose(backStagePass5DaysAway);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);

    }

}
