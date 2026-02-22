# 🌑 Stíny Zaklínače

**Stíny Zaklínače** je textová adventura napsaná v Javě, kde se hráč probudí jako mladý čaroděj bez vzpomínek uprostřed prokletých bažin. Celý svět je stižen temnou kletbou, která mění lidi v nestvůry. Vašim úkolem je prozkoumat nebezpečné lokace, komunikovat s pozůstalými bytostmi, splnit jejich questy a získat 6 rituálních artefaktů. Pouze tak můžete zlomit kletbu a najít cestu domů.

### 🎮 Herní mechaniky

* **Prozkoumávání** - Pohybujte se mezi 10 lokacemi pomocí příkazů
* **Inventář** - Sbírejte předměty (max 10) a strategicky je používejte
* **Questy** - Plňte úkoly pro NPC a získávejte odměny
* **Souboje** - Poražte nepřátelského Arkanistu pomocí magie
* **Hádanky** - Odpovídejte správně na záludné otázky

### 🏆 Cíl hry

Získej **6 rituálních artefaktů**:
1. 💨 Bažinný plyn (`swamp_gas`)
2. 🌾 Magická mouka (`magic_flour`)  
3. 🌿 Věčná větvička (`eternal_twig`)
4. ❄️ Mrazivý kámen (`frosty_stone`)
5. 📜 Runový kámen (`rune_stone`)
6. ✨ Fragment kouzla (`spell_fragment`)

Poté je umístěte na oltář v **Druidově hvězdném oltáři** a zlomte kletbu!

### 🚶První kroky ve hře

```
> examine
=== Rotting Marshlands ===
You wake with a pounding headache on cold...

Items here:
  - glasses (Glasses) 

Characters here:
  - swamp_wanderer (Swamp Wanderer)

Available exits:
  - forsaken_town (Forsaken Town)

> take glasses
You have picked up: Glasses

> inventory
=== Your Inventory (2/10) ===
- magic_wand
- glasses

> talk swamp_wanderer
Swamp Wanderer says:
"I... I can't see properly. My glasses! I lost my glasses...
```

**💡 Tip:** Použijte příkaz `help` pro zobrazení seznam všech dostupných příkazů!

---

## 🗺️ Mapa světa

```
                    ┌─────────────────────┐
                    │   Hnijící tůně 💀   │
                    │(Rooting Marshlands) │
                    │      ⭐ START       │
                    └──────────┬──────────┘
                               │
                    ┌──────────┴──────────┐
        ┌───────────┤  Opuštěné město 🏚️  ├──────────┐
        │           │  (Forsaken Town)    │          │
        │           └─────────┬───────────┘          │
        │                     │                      │
        │           ┌─────────┴────────────┐         │
        │           │     Radniční věz🗼   │         │
        │           │      (Town Hall)     │         │
        │           └──────────────────────┘         │
        │                                            │
┌───────┴────────┐                          ┌────────┴────────┐
│ Mýtina 🌸      │                          │ Větrný mlýn 🌾 │
│ (Sunny Glade)  │                          │ (Windmill)      │
└───────┬────────┘                          └─────────────────┘         
        │                                             
┌───────┴────────┐                          ┌───────┴──────────┐                         
│Štíty mrazivého │                          │ Šeptající les 🌲 │                        
│    dechu ❄️    │                          │(Whispering Woods)│
│  (Frost Peaks) │                          └───────┬──────────┘                       
└───────┬────────┘                                  │                         
        │                                           │ 
        │           ┌────────────────────┐          │ 
        └───────────┤   Prastarý dub 🌳  ├──────────┘
                    │    (Ancient Oak)   │
                    └─────────┬──────────┘
                              │
                    ┌─────────┴────────────┐
                    │ Jeskyně zapomenutí🕳️ │
                    │(Caverns of Oblivion) │
                    └─────────┬────────────┘
                              │
                    ┌─────────┴──────────────┐
                    │Druidův hvězdný oltář ⭐│
                    │(Caverns of Oblivion)   │
                    └────────────────────────┘
```

---

## 🧙‍♀️ Seznam Postav

| Jméno (CZ) | Jméno (EN) | ID |
|---------|--------|--------|
| 🧟 Bažinný bludišťák | Swamp Wanderer | swamp_wanderer |
| 💀 Kostlivec Hugo | Skeleton Hugo | skeleton_hugo |
| 🤺 Arkanista Malrec | Arcanist Malrec | arcanist_malrec |
| 🧚 Lesní víla | Forest Fairy | forest_fairy |
| 🗻 Průzkumník Gerald | Explorer Gerald | explorer_gerald  |
| 🌳 Dub moudrosti | Oak of Wisdom | oak_of_wisdom |

## 🪄 Seznam Předmětů 

| Jméno (CZ) | Jméno (EN) | ID |
|---------|--------|--------|
| 👓 Brýle | Glasses | glasses |
| 🪄 Magická hůl | Magic Wand | magic_wand |
| 🔑 Zrezivělý klíč | Rusty Key | rusty_key |
| 💡 Krystal světla | Light Crystal | light_crystal |
| 🌫️ Bažinný plyn | Swamp Gas | swamp_gas  |
| 🧚‍♂️ Magická mouka | Magic Flour | magic_flour |
| 🧊 Mrazivý kyz | Forsty Quartz | frosty_quartz |
| 🗻 Runový kámen | Rune Stone | rune_stone |
| 🧙‍♀️ Fragment kouzla | Spell Fragment | spell_fragment |
| 🧪 Elixír uzdravení | Healing Elixir | healing_elixir  |
| 🪾 Věčná větvička | Eternal Twig | eternal_twig |

## 🌋Seznam Lokací

| Jméno (CZ) | Jméno (EN) | ID |
|---------|--------|--------|
| 💀 Hnijící tůně | Rotting Marshlands | rotting_marshlands |
| 🏚️ Opuštěné město | Forsaken Town | forsaken_town |
| 🗼 Radniční věž | Town Hall Tower | town_hall |
| 🌸 Mýtina | Sunny Glade | sunny_glade |
| ❄️ Štíty mrazivého dechu | Frozen Peaks | frozen_peaks  |
| 🌾 Vetrný mlýn | Windmill | windmill |
| 🌲 Šeptající les | Whispering Woods | whispering_woods |
| 🌳 Prastarý dub moudrosti | Ancient Oak of Wisdom | ancient_oak |
| 🕳️ Jeskyně zapomenutí | Caverns of Oblivion | caverns_of_oblivion |
| ⭐ Druidův hvězdný oltář | Druid's Star Circle | druids_star_circle  |

## 📋 Seznam příkazů

| Příkaz | Popis | Příklad |
|--------|-------|---------|
| `move <id_lokace>` | Přesun do jiné lokace | `move abandoned_town` |
| `take <id_předmětu>` | Sebrat předmět | `take crystal_light` |
| `drop <id_předmětu>` | Zahodit předmět | `drop rusty_key` |
| `use <id_předmětu>` | Použít předmět | `use magic_wand` |
| `talk <id_postavy>` | Mluvit s postavou | `talk skeleton_hugo` |
| `examine` | Prozkoumat lokaci | `examine` |
| `look` | Rozhlédnout se | `look` |
| `inventory` | Zobrazit inventář | `inventory` |
| `help` | Zobrazit nápovědu | `help` |
| `hint` | Kontextová nápověda | `hint` |
| `quit` | Ukončit hru | `quit` |

---
## Klíčové tipy
* 💡 Inventář má limit **10 předmětů** - zahazuj nepotřebné věci
* 💡 Některé lokace jsou **zamčené** - potřebuješ správný klíč
* 💡 Druidův hvězdný kruh se odemkne až po **dokončení všech 6 questů**
* 💡 Používej `hint` pro nápovědu když nevíš co dál
* 💡 Krystal světla je **nezbytný** pro temné jeskyně
