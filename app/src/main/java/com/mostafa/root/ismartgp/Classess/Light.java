package com.smarthomecontrolusingspeech.ismart.Model;

/**
 * Created by Belal on 12/03/2018.
 */

public class Light {

        private boolean isOpened = false;

        public Light(boolean isOpened) {
            this.isOpened = isOpened;
        }

        public boolean isOpened() {
            return isOpened;
        }

        public void setOpened(boolean opened) {
            isOpened = opened;

        }


    }


