package racingcar.controller;

import racingcar.Constants;
import racingcar.model.Car;
import racingcar.view.InputView;
import racingcar.model.Cars;
import racingcar.view.OutputView;

import java.util.Scanner;

public class RacingManager {
    private Cars cars;
    private int userTrialCount;

    public RacingManager() {

    }

    public void startRace(Scanner scanner) {
        System.out.println(Constants.PROMPT_USER_NAME);
        cars = createCars(scanner);
        System.out.println(Constants.PROMPT_USER_TRIAL_COUNT);
        userTrialCount = createTrialCount(scanner);

        System.out.println(Constants.NEW_LINE + Constants.RACING_START);

        for (int round = 0; round < userTrialCount; round++) {
            runCars();
            OutputView.printRacingStatus(cars);
        }

        cars.selectWinners();
        OutputView.printResult(cars);
    }

    public void runCars() {
        for (Car car : cars.getCars()) {
            car.moveCar();
        }
    }

    public Cars createCars(Scanner scanner) {
        boolean isNeedInput = true;
        while (isNeedInput) {
            try {
                return new Cars(InputView.getUserNames(scanner));
            } catch (IllegalArgumentException e) {
                System.err.println(Constants.ERR_USER_NAME);
                isNeedInput = true;
            }
        }
        return null;
    }

    public int createTrialCount(Scanner scanner) {
        boolean isNeedInput = true;
        while (isNeedInput) {
            try {
                String userTrialCount = InputView.getTrialCount(scanner);
                return Integer.parseInt(userTrialCount);
            } catch (IllegalArgumentException e) {
                System.err.println(Constants.ERR_USER_TRIAL_COUNT);
                isNeedInput = true;
            }
        }
        return 0;
    }
}

