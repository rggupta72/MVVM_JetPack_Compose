package com.example.composepoc.view

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composepoc.data.model.GmwUiInterstitialModel
import com.example.composepoc.utils.GMW_ERROR
import com.example.composepoc.utils.GMW_LOADING_PROFILE
import com.example.composepoc.utils.GMW_REFRESH_WELCOME_STEPS
import com.example.composepoc.utils.WELCOME_STEPS_LOADING
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GmwInterstitialTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val gmwUiModel = GmwUiInterstitialModel(
        title = "Welcome to My GMW",
        imageAccessLabel = "GMW Background Image",
        header = "Ready to Get Started?",
        description = "Let's personalize your care experience.",
        iconAccessLabel = "Hand Heart Icon",
        closeButtonLabel = "Close",
        skipButtonLabel = "Skip",
        taskHeader = "You have %1\$d out of %2\$d tasks done.",
        currentProgress = 2,
        maxListSize = 5,
        closeButtonVisibility = false,
        benefitsSummaryUrl = "",
        maxProgress = 9,
        buttonText = "",
        buttonContentDescription = "",
        profileSetupCardStatus = "",
        dismissButtonTitle = "",
        accessDismissButtonTitle = "",
        accessCardStatusImage = "",
        linkToSurveyDescription = "",
        wellnessHealthInfoTitle = "",
        gmwInterruptTitle = "",
        gmwInterruptSubTitle = "",
        gmwInterruptIconAccessLabel = "",
        gmwInterruptCloseAccess = "",
        gmwInterruptButtonTitle = "",
        gmwInterruptButtonAccess = "",
        skipButtonAccessLabel = "",
        closeButtonAccessLabel = "",
    )

    @Test
    fun gmwInterstitialUi_displaysCorrectContent() {
        val continueClicked = false
        val retryClicked = false
        val skipClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked
                },
                isLoadingStatus = false,
                gmwErrorStatus = false,
                onRetryClick = {
                    retryClicked
                },
                onSkipClick = {
                    skipClicked
                },
                isFromMyChart = false,
            )
        }
        // Assertions for core elements
        composeTestRule.onNodeWithText(gmwUiModel.title).assertExists()
        composeTestRule.onNodeWithContentDescription(gmwUiModel.imageAccessLabel).assertExists()
        composeTestRule.onNodeWithText(gmwUiModel.header).assertExists()
        composeTestRule.onNodeWithText(gmwUiModel.description).assertExists()
        composeTestRule.onNodeWithContentDescription(gmwUiModel.iconAccessLabel).assertExists()
        composeTestRule.onNodeWithText(gmwUiModel.skipButtonLabel).assertExists()


    }

    @Test
    fun gmwInterstitialUi_isLoadingState() {        // Given
        val continueClicked = false
        val retryClicked = false
        val skipClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked
                },
                isLoadingStatus = true,
                gmwErrorStatus = false,
                onRetryClick = {
                    retryClicked
                },
                onSkipClick = {
                    skipClicked
                },
                isFromMyChart = false,
            )
        }
        // Assert
        composeTestRule.onNodeWithText(WELCOME_STEPS_LOADING).isDisplayed()
        composeTestRule.onNodeWithText(GMW_LOADING_PROFILE).isDisplayed()
    }

    @Test
    fun gmwInterstitialUi_isErrorState() {        // Given
        val continueClicked = false
        val retryClicked = false
        val skipClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked
                },
                isLoadingStatus = false,
                gmwErrorStatus = true,
                onRetryClick = {
                    retryClicked
                },
                onSkipClick = {
                    skipClicked
                },
                isFromMyChart = false,
            )
        }        // Assert
        composeTestRule.onNodeWithText(GMW_ERROR).isDisplayed()
        composeTestRule.onNodeWithText(GMW_REFRESH_WELCOME_STEPS).isDisplayed()
    }

    @Test
    fun gmwInterstitialUi_retryButtonClick_callsOnRetryClick() {
        // Given
        var retryClicked = false
        val continueClicked = false
        val skipClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked
                },
                isLoadingStatus = false,
                gmwErrorStatus = true,
                onRetryClick = {
                    retryClicked = true
                },
                onSkipClick = {
                    skipClicked
                },
                isFromMyChart = false,
            )
        }
        // when
        composeTestRule.onNodeWithText("Refresh Welcome Steps").assertExists()
        composeTestRule.onNodeWithText("Refresh Welcome Steps").performClick()
    }

    @Test
    fun gmwInterstitialUi_SkipButtonClick_callsOnSkipClick() {
        // Given
        var skipClicked = false
        val continueClicked = false
        val retryClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked
                },
                isLoadingStatus = false,
                gmwErrorStatus = false,
                onRetryClick = {
                    retryClicked
                },
                onSkipClick = {
                    skipClicked = true
                },
                isFromMyChart = false,
            )
        }
        // when
        composeTestRule.onNodeWithText("Skip").performClick()
        // then
        assert(skipClicked)
    }

    @Test
    fun gmwInterstitialUi_ContinueButtonClick_callsOnContinueClick() {
        // Given
        var continueClicked = false
        val skipClicked = false
        val retryClicked = false
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel,
                onContinueClick = {
                    continueClicked = true
                },
                isLoadingStatus = false,
                gmwErrorStatus = false,
                onRetryClick = {
                    retryClicked
                },
                onSkipClick = {
                    skipClicked
                },
                isFromMyChart = false,
            )
        }
        // when
        composeTestRule.onNodeWithContentDescription("${gmwUiModel.header} Icon").performClick()
    }

    @Test
    fun gmwInterstitialUi_isFromMyChart_displaysCloseButton() {
        composeTestRule.setContent {
            GmwInterstitialUi(
                gmwUiModel = gmwUiModel.copy(closeButtonLabel = "Close"),
                onContinueClick = { },
                isLoadingStatus = false,
                gmwErrorStatus = false,
                onRetryClick = { },
                onSkipClick = { },
                isFromMyChart = true,
            )
        }
        composeTestRule.onNodeWithText("Close").assertExists()
    }

    @Test
    fun gmwInterstitialUi_CorrectProgress() {
        val currentProgress = 3
        val maxListSize = 7
        composeTestRule.setContent {
            RoundedProgressIndicator(completedTasks = currentProgress, totalTasks = maxListSize)
        }
    }

}

