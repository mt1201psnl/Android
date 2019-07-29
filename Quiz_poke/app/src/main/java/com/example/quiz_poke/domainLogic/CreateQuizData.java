package com.example.quiz_poke.domainLogic;

import com.example.quiz_poke.vo.PokemonVo;

import java.util.LinkedList;
import java.util.Random;

public class CreateQuizData {

    public LinkedList<PokemonVo> execute(LinkedList<PokemonVo> allRecordList, int quizVolume) {

        LinkedList<PokemonVo> quizData = new LinkedList<>();

        for (int quizVolumeCount = 0; quizVolumeCount < quizVolume; quizVolumeCount++) {

            if (allRecordList.size() == 0) {
                break;
            }

            Random random = new Random();
            int randomNum = random.nextInt(allRecordList.size());

            PokemonVo tmpVo1 = new PokemonVo();
            PokemonVo tmpVo2 = allRecordList.get(randomNum);

            tmpVo1.setImage(tmpVo2.getImage());
            tmpVo1.setName(tmpVo2.getName());
            tmpVo1.setType_1(tmpVo2.getType_1());
            tmpVo1.setType_2(tmpVo2.getType_2());
            tmpVo1.setCharacteristic_1(tmpVo2.getCharacteristic_1());
            tmpVo1.setCharacteristic_2(tmpVo2.getCharacteristic_2());
            tmpVo1.setCharacteristic_dream(tmpVo2.getCharacteristic_dream());
            tmpVo1.setSpecies(tmpVo2.getSpecies());
            tmpVo1.setMost_used_move(tmpVo2.getMost_used_move());

            quizData.add(tmpVo1);

            allRecordList.remove(randomNum);
        }

        return quizData;
    }
}
