package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashMap;
import java.util.Map;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static Race inputCars() {

        System.out.println("경주 할 자동차 이름(이름은 쉼표(,) 기준으로 구분)");
        //자동차 이름 입력
        String cars = readLine();
        //쉼표로 구분
        String[] car = cars.split(",");

        int carNum = car.length;

        //5자 이하만 가능하도록 제한
        for(String name : car) {
            if(name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }

        //횟수 입력
        System.out.println("시도할 회수는 몇회인가요?");
        int num = Integer.parseInt(readLine());

        //조건 확인합니다. 자동차는 두 대 이상이어야 승부가 가능하고, 시도할 횟수도 1회 이상이어야 한다.
        if(carNum < 2 || num < 1){
            throw new IllegalArgumentException();
        }

        Race race = new Race(car, num);


        return race;
    }

    public static class Race{
        private String[] cars;
        private int num;

        public Race(String[] cars, int num) {
            this.cars = cars;
            this.num = num;
        }

        public String[] getCars() {
            return cars;
        }

        public int getNum() {
            return num;
        }
    }

    // 차 경주 시작
    public static void start(Race race){
        String[] car = race.getCars();
        int num = race.getNum();

        int carNum = car.length;
        int[] carPos = new int[carNum];
        int maxPos = 0;

        System.out.println();
        System.out.println("실행 결과");
        for (int gameCnt = 0; gameCnt < num; gameCnt++) {
            for (int carIndex = 0; carIndex < carNum; carIndex++) {
                //무작위 값 입력
                int rand = Randoms.pickNumberInRange(0, 9);
                //4보다 크면 이동
                if (rand >= 4) {
                    carPos[carIndex]++;
                }

                //이동한 경우 위치값을 저장한다.
                maxPos = Math.max(maxPos, carPos[carIndex]);
            }

            // 각 차의 이동 상황 출력
            for (int carIndex = 0; carIndex < carNum; carIndex++) {
                System.out.print(car[carIndex] + " : ");
                for (int i = 0; i < carPos[carIndex]; i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }


        //최종우승자 판별
        StringBuilder win = new StringBuilder();
        for (int carIndex = 0; carIndex < carNum; carIndex++) {
            if (carPos[carIndex] == maxPos) {
                if (win.length() > 0) {
                    win.append(", ");
                }
                win.append(car[carIndex]);
            }
        }

        if (win.toString().contains(", ")) {
            System.out.println("최종 우승자: " + win);
        } else {
            System.out.println("최종 우승자: " + win.toString());
        }
    }


    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //입력
        Race race = inputCars();

        //경주 시작과 최종 우승자 판별
        start(race);


    }



}
