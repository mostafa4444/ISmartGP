package com.mostafa.root.ismartgp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mmmmmm";
    public static final String TABLE_NAME = "mmmm";
    public static final String KEY_ID = "action_id";
    public static final String KEY_CURRENT = "current_action";
    public static final String KEY_NEXT = "next_action";
    public static final String KEY_COUNT = "action_count";

    public HomeHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACTION_TABLE =  "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_CURRENT+" TEXT, "+KEY_NEXT+" TEXT,"
                +KEY_COUNT+" INTEGER)";
        db.execSQL(CREATE_ACTION_TABLE);
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('2' , 'a' , 'b' , '100')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('3' , 'a' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('4' , 'a' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('5' , 'a' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('6' , 'a' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('7' , 'a' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('8' , 'a' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('9' , 'a' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('10' , 'a' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('11' , 'a' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('12' , 'a' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('13' , 'a' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('14' , 'a' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('15' , 'a' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('16' , 'a' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('17' , 'a' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('18' , 'a' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('19' , 'a' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('20' , 'a' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('21' , 'a' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('22' , 'a' , 'w' , '0')");
//---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('23' , 'b' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('25' , 'b' , 'c' , '36')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('26' , 'b' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('27' , 'b' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('28' , 'b' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('29' , 'b' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('30' , 'b' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('31' , 'b' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('32' , 'b' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('33' , 'b' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('34' , 'b' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('35' , 'b' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('36' , 'b' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('37' , 'b' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('38' , 'b' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('39' , 'b' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('40' , 'b' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('41' , 'b' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('42' , 'b' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('43' , 'b' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('44' , 'b' , 'w' , '100')");

        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('45' , 'c' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('46' , 'c' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('48' , 'c' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('49' , 'c' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('50' , 'c' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('51' , 'c' , 'g' , '40')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('52' , 'c' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('53' , 'c' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('54' , 'c' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('55' , 'c' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('56' , 'c' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('57' , 'c' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('58' , 'c' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('59' , 'c' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('60' , 'c' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('61' , 'c' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('62' , 'c' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('63' , 'c' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('64' , 'c' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('65' , 'c' , 'v' , '100')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('66' , 'c' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('67' , 'd' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('68' , 'd' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('69' , 'd' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('71' , 'd' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('72' , 'd' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('73' , 'd' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('74' , 'd' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('75' , 'd' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('76' , 'd' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('77' , 'd' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('78' , 'd' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('79' , 'd' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('80' , 'd' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('81' , 'd' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('82' , 'd' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('83' , 'd' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('84' , 'd' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('85' , 'd' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('86' , 'd' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('87' , 'd' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('88' , 'd' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('89' , 'e' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('90' , 'e' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('91' , 'e' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('92' , 'e' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('94' , 'e' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('95' , 'e' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('96' , 'e' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('97' , 'e' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('98' , 'e' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('99' , 'e' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('100' , 'e' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('101' , 'e' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('102' , 'e' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('103' , 'e' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('104' , 'e' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('105' , 'e' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('106' , 'e' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('107' , 'e' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('108' , 'e' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('109' , 'e' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('110' , 'e' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('111' , 'f' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('112' , 'f' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('113' , 'f' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('114' , 'f' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('115' , 'f' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('117' , 'f' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('118' , 'f' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('119' , 'f' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('120' , 'f' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('121' , 'f' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('122' , 'f' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('123' , 'f' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('124' , 'f' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('125' , 'f' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('126' , 'f' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('127' , 'f' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('128' , 'f' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('129' , 'f' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('130' , 'f' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('131' , 'f' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('132' , 'f' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('133' , 'g' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('134' , 'g' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('135' , 'g' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('136' , 'g' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('137' , 'g' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('138' , 'g' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('140' , 'g' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('141' , 'g' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('142' , 'g' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('143' , 'g' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('144' , 'g' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('145' , 'g' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('146' , 'g' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('147' , 'g' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('148' , 'g' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('149' , 'g' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('150' , 'g' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('151' , 'g' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('152' , 'g' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('153' , 'g' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('154' , 'g' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('155' , 'h' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('156' , 'h' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('157' , 'h' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('158' , 'h' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('159' , 'h' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('160' , 'h' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('161' , 'h' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('163' , 'h' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('164' , 'h' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('165' , 'h' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('166' , 'h' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('167' , 'h' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('168' , 'h' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('169' , 'h' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('170' , 'h' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('171' , 'h' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('172' , 'h' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('173' , 'h' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('174' , 'h' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('175' , 'h' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('176' , 'h' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('177' , 'i' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('178' , 'i' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('179' , 'i' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('180' , 'i' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('181' , 'i' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('182' , 'i' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('183' , 'i' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('184' , 'i' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('186' , 'i' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('187' , 'i' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('188' , 'i' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('189' , 'i' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('190' , 'i' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('191' , 'i' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('192' , 'i' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('193' , 'i' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('194' , 'i' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('195' , 'i' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('196' , 'i' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('197' , 'i' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('198' , 'i' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('199' , 'j' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('200' , 'j' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('201' , 'j' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('202' , 'j' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('203' , 'j' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('204' , 'j' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('205' , 'j' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('206' , 'j' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('207' , 'j' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('209' , 'j' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('210' , 'j' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('211' , 'j' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('212' , 'j' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('213' , 'j' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('214' , 'j' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('215' , 'j' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('216' , 'j' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('217' , 'j' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('218' , 'j' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('219' , 'j' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('220' , 'j' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('221' , 'k' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('222' , 'k' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('223' , 'k' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('224' , 'k' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('225' , 'k' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('226' , 'k' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('227' , 'k' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('228' , 'k' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('229' , 'k' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('230' , 'k' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('232' , 'k' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('233' , 'k' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('234' , 'k' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('235' , 'k' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('236' , 'k' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('237' , 'k' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('238' , 'k' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('239' , 'k' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('240' , 'k' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('241' , 'k' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('242' , 'k' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('243' , 'w' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('244' , 'w' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('245' , 'w' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('246' , 'w' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('247' , 'w' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('248' , 'w' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('249' , 'w' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('250' , 'w' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('251' , 'w' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('252' , 'w' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('253' , 'w' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('254' , 'w' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('255' , 'w' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('256' , 'w' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('257' , 'w' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('258' , 'w' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('259' , 'w' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('260' , 'w' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('261' , 'w' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('262' , 'w' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('263' , 'w' , 'v' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('265' , 'm' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('266' , 'm' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('267' , 'm' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('268' , 'm' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('269' , 'm' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('270' , 'm' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('271' , 'm' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('272' , 'm' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('273' , 'm' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('274' , 'm' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('275' , 'm' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('277' , 'm' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('278' , 'm' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('279' , 'm' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('280' , 'm' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('281' , 'm' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('282' , 'm' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('283' , 'm' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('284' , 'm' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('285' , 'm' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('286' , 'm' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('287' , 'n' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('288' , 'n' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('289' , 'n' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('290' , 'n' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('291' , 'n' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('292' , 'n' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('293' , 'n' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('294' , 'n' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('295' , 'n' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('296' , 'n' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('297' , 'n' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('298' , 'n' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('300' , 'n' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('301' , 'n' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('302' , 'n' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('303' , 'n' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('304' , 'n' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('305' , 'n' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('306' , 'n' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('307' , 'n' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('308' , 'n' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('309' , 'o' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('310' , 'o' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('311' , 'o' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('312' , 'o' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('313' , 'o' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('314' , 'o' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('315' , 'o' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('316' , 'o' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('317' , 'o' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('318' , 'o' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('319' , 'o' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('320' , 'o' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('321' , 'o' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('323' , 'o' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('324' , 'o' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('325' , 'o' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('326' , 'o' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('327' , 'o' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('328' , 'o' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('329' , 'o' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('330' , 'o' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('331' , 'p' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('332' , 'p' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('333' , 'p' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('334' , 'p' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('335' , 'p' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('336' , 'p' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('337' , 'p' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('338' , 'p' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('339' , 'p' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('340' , 'p' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('341' , 'p' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('342' , 'p' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('343' , 'p' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('344' , 'p' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('346' , 'p' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('347' , 'p' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('348' , 'p' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('349' , 'p' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('350' , 'p' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('351' , 'p' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('352' , 'p' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('353' , 'q' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('354' , 'q' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('355' , 'q' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('356' , 'q' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('357' , 'q' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('358' , 'q' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('359' , 'q' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('360' , 'q' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('361' , 'q' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('362' , 'q' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('363' , 'q' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('364' , 'q' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('365' , 'q' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('366' , 'q' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('367' , 'q' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('369' , 'q' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('370' , 'q' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('371' , 'q' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('372' , 'q' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('373' , 'q' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('374' , 'q' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('375' , 'r' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('376' , 'r' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('377' , 'r' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('378' , 'r' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('379' , 'r' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('380' , 'r' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('381' , 'r' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('382' , 'r' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('383' , 'r' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('384' , 'r' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('385' , 'r' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('386' , 'r' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('387' , 'r' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('388' , 'r' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('389' , 'r' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('390' , 'r' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('392' , 'r' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('393' , 'r' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('394' , 'r' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('395' , 'r' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('396' , 'r' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('397' , 's' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('398' , 's' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('399' , 's' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('400' , 's' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('401' , 's' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('402' , 's' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('403' , 's' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('404' , 's' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('405' , 's' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('406' , 's' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('407' , 's' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('408' , 's' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('409' , 's' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('410' , 's' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('411' , 's' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('412' , 's' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('413' , 's' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('415' , 's' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('416' , 's' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('417' , 's' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('418' , 's' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('419' , 't' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('420' , 't' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('421' , 't' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('422' , 't' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('423' , 't' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('424' , 't' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('425' , 't' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('426' , 't' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('427' , 't' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('428' , 't' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('429' , 't' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('430' , 't' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('431' , 't' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('432' , 't' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('433' , 't' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('434' , 't' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('435' , 't' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('436' , 't' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('438' , 't' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('439' , 't' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('440' , 't' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('441' , 'u' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('442' , 'u' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('443' , 'u' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('444' , 'u' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('445' , 'u' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('446' , 'u' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('447' , 'u' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('448' , 'u' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('449' , 'u' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('450' , 'u' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('451' , 'u' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('452' , 'u' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('453' , 'u' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('454' , 'u' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('455' , 'u' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('456' , 'u' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('457' , 'u' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('458' , 'u' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('459' , 'u' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('461' , 'u' , 'v' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('462' , 'u' , 'w' , '0')");
        //---------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('463' , 'v' , 'a' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('464' , 'v' , 'b' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('465' , 'v' , 'c' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('466' , 'v' , 'd' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('467' , 'v' , 'e' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('468' , 'v' , 'f' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('469' , 'v' , 'g' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('470' , 'v' , 'h' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('471' , 'v' , 'i' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('472' , 'v' , 'j' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('473' , 'v' , 'k' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('474' , 'v' , 'm' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('475' , 'v' , 'n' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('476' , 'v' , 'o' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('477' , 'v' , 'p' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('478' , 'v' , 'q' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('479' , 'v' , 'r' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('480' , 'v' , 's' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('481' , 'v' , 't' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('482' , 'v' , 'u' , '0')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('484' , 'v' , 'w' , '0')");
//-----------------------------------------------------------------------------------------------------------------
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public void addNewAction(HomeAction homeAction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID ,homeAction.getAction_id());
        values.put(KEY_CURRENT , homeAction.getCurrent_action());
        values.put(KEY_NEXT , homeAction.getNext_action());
        values.put(KEY_COUNT , homeAction.getAction_count());
        db.insert(TABLE_NAME , null , values);
        db.close();
    }

    public List<HomeAction> getAllAction(){
        List<HomeAction> chartList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){
            do{
                HomeAction chart = new HomeAction();
                chart.setAction_id(String.valueOf(Integer.parseInt(cursor.getString(0))));
                chart.setCurrent_action(cursor.getString(1));
                chart.setNext_action(cursor.getString(2));
                chart.setAction_count(Integer.parseInt((cursor.getString(3))));
                chartList.add(chart);

            }while(cursor.moveToNext());
        }
        db.close();
        return chartList;
    }
    public int getActionRow(){
        String countQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery , null);
        cursor.close();
        return cursor.getCount();
    }



    public void deleteRaw(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+KEY_ID+" ="+id+"");
        db.close();
    }

    public void updataSingleAction(String current , String next , int count){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+KEY_COUNT+" = '"+count+"' WHERE "+KEY_CURRENT+" ='"+current+"' AND "+KEY_NEXT+" ='"+next+"'");
        db.close();
    }

    public List<HomeAction> getCOUNT(String current ,String next){
        List<HomeAction> homeActionList = new ArrayList<>();
        String selectQuery = "SELECT "+KEY_COUNT+" FROM " +TABLE_NAME+" WHERE "+KEY_CURRENT+" ='"+current+"' AND "+KEY_NEXT+"='"+next+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){
            do{
                HomeAction homeAction = new HomeAction();
                homeAction.setAction_count(Integer.parseInt(cursor.getString(0)));

                homeActionList.add(homeAction);

            }while(cursor.moveToNext());
        }
        db.close();
        return homeActionList;
    }




    public List<HomeAction> getMaxCount(String current){
        List<HomeAction> homeActionList = new ArrayList<>();
        String selectQuery = "SELECT max("+KEY_COUNT+") FROM "+TABLE_NAME+" WHERE "+KEY_CURRENT+" ='"+current+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){
            do{
                HomeAction homeAction = new HomeAction();
                homeAction.setAction_count(Integer.parseInt(cursor.getString(0)));
                homeActionList.add(homeAction);

            }while(cursor.moveToNext());
        }
        return homeActionList;
    }


    public List<HomeAction> getExecptedNext(String current , int count){
        List<HomeAction> homeActionList = new ArrayList<>();
        String selectQuery = "SELECT "+KEY_NEXT+" FROM "+TABLE_NAME+" WHERE "+KEY_CURRENT+" ='"+current+"' AND "+KEY_COUNT+" ='"+count+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){
            do{
                HomeAction homeAction = new HomeAction();
                homeAction.setNext_action(cursor.getString(0));
                homeActionList.add(homeAction);

            }while(cursor.moveToNext());
        }
        return homeActionList;
    }




}
