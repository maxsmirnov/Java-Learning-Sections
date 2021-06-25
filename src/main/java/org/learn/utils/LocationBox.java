package org.learn.utils;

import org.learn.entities.ButtonLocation;

public class LocationBox {
    private int cell1;
    private int cell2;
    private int cell3;
    private int cell4;
    private int currentCell = 1;
    private ButtonLocation currentLoc = new ButtonLocation();

    private int maxColumn = 9;
    private int maxRow = 24;

    public void put() {
        if (currentCell == 1) {
            currentLoc = new ButtonLocation(cell1, 0);
            cell1++;
        } else if (currentCell == 2) {
            currentLoc = new ButtonLocation(cell2, maxColumn);
            cell2++;
        } else if (currentCell == 3) {
            currentLoc = new ButtonLocation(maxRow - cell3, 0);
            cell3++;
        } else if (currentCell == 4) {
            currentLoc = new ButtonLocation(maxRow - cell4, maxColumn);
            cell4++;
        }
        currentCell++;
        if (currentCell > 4) {
            currentCell = 1;
        }
    }

    public ButtonLocation getCurrentLock() {
        return currentLoc;
    }
}
