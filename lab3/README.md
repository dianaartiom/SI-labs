### Intro
In this laboratory work, my main task was to decrypt three messages:

1. WKHSWEC: WI XKWO SC WKHSWEC NOMSWEC WOBSNSEC, MYWWKXNOB YP DRO KBWSOC YP DRO XYBDR, QOXOBKV YP DRO POVSH VOQSYXC, VYIKV COBFKXD DY DRO DBEO OWZOBYB, WKBMEC KEBOVSEC. PKDROB DY K WEBNOBON CYX, RECLKXN DY K WEBNOBON GSPO. KXN S GSVV RKFO WI FOXQOKXMO, SX DRSC VSPO YB DRO XOHD.

2. XOXKR IETG BL MH UX VHGLBWXKXW, XOXKR XQIXWBXGM MKBXW TGW XOXKR FXMAHW MTDXG UXYHKX FTMMXKL TKX UKHNZAM MH MABL ETLM XQMKXFBMR. ZHHW HYYBVXKL WXVEBGX ZXGXKTE XGZTZXFXGML PAXKX MAX HWWL TKX MHH ZKXTM, TGW IKXYXK MAX XFIEHRFXGM HY LMKTMTZXF TGW YBGXLLX MH WXLMKHR MAX XGXFR TL FNVA TL IHLLBUEX PBMAHNM XQIHLBGZ MAXBK HPG YHKVXL.

3. RD QTAJ KTW YMJ WTRFS JRUNWJ NX ZSIJSNFGQD LWJFYJW YMFS KTW RDXJQK. YMJ LWJFYJXY JRUNWJ JAJW YT MFAJ JCNXYJI. N UQJILJ RD JYJWSFQ XJWANYZIJ FSI N FR KTWJAJW GTZSI YT XJWAJ NY, NS QNKJ FSI NS IJFYM. YMJD MFAJ RJWJQD LNAJS ZX: WTFIX, HJSYWFQ MJFYNSL, HTSHWJYJ, YMJ HFQJSIFW, FSI KQZXMNSL YTNQJYX FSI XJBJWX.

### Work procedure
##### Tools and technologies used
Language - Java <br />
Build and Dependency Mngmt - Maven <br />
VCS - Git <br />

##### Implementation logic: 
The input string is analyzed. A temporary ``` percentage ``` variable is used to check how many of the current words in string are found in the language dictionary. If the percentage is less than 20%, the shifted string is returned; otherwise the string is shifted once more time. This loop repeats several times (depending on how many letters are there in that languge), until a fair percentage value is found.

``` 
compare(input, dictionary) {
 toLowCase(input)
 if % match > 20 return input
 else shifRight(input)
 }
}
```

In order to make the program extensible for many more languages, I have split the logic, giving the developer the possibility to add the as many languages as (s)he wants, and to implement the logic for matchers and shifters only. The following images explain the architecture of the project. <br />

**Language** is an abstract class having only the responsibility to load the dictionary, according to the name. It is an abstract class. The only ones to be instantiated are the child classes EnLanguage and RoLanguage. Note that any number of languages can be added.This can be simply be done by creating a NeLangClass, extend the Language class and assign a value for name as follows:
```java
public class NewLangClass extends Language {

    public NewLangClass() {
        this.name = "english";
    }
}
```
The dictionary of the language is a top 100 frequent words loaded in a file in resources folder.  

![alt text](img/languages.png "Languages")

Same straightforward logic is being followed for the matchers and shifters of each language added. 

##### Answers
For the deadline:
```
[hi, guys, the, deadline, for, this, laboratory, is, the, twentieth, of, december, i, would, like, to, wish, you, good, luck, with, it, and, have, a, nice, life]
```

After shifting the given input strings, the following was obtained:

```
[maximusJ, my, name, is, maximus, decimus, meridius<, commander, of, the, armies, of, the, north<, general, of, the, felix, legions<, loyal, servant, to, the, true, emperor<, marcus, aurelius>, father, to, a, murdered, son<, husband, to, a, murdered, wife>, and, i, will, have, my, vengeance<, in, this, life, or, the, next>]
```

```
[every, plan, is, to, be, considered3, every, expedient, tried, and, every, method, taken, before, matters, are, brought, to, this, last, extremity5, good, officers, decline, general, engagements, where, the, odds, are, too, great3, and, prefer, the, employment, of, stratagem, and, finesse, to, destroy, the, enemy, as, much, as, possible, without, exposing, their, own, forces5]
```

```
[my, love, for, the, roman, empire, is, undeniably, greater, than, for, myselfC, the, greatest, empire, ever, to, have, existedC, i, pledge, my, eternal, servitude, and, i, am, forever, bound, to, serve, itA, in, life, and, in, deathC, they, have, merely, given, usO, roadsA, central, heatingA, concreteA, the, calendarA, and, flushing, toilets, and, sewersC]
```
