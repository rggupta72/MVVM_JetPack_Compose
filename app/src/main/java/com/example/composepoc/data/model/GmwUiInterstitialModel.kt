package com.example.composepoc.data.model

data class GmwUiInterstitialModel(
    val header: String,
    val description: String,
    val taskHeader: String,
    val benefitsSummaryUrl: String,
    val currentProgress: Int,
    val maxListSize: Int,
    val maxProgress: Int = 10,
    val buttonText: String,
    val buttonContentDescription: String,
    val closeButtonVisibility: Boolean,
    val profileSetupCardStatus: String,
    val dismissButtonTitle: String,
    val accessDismissButtonTitle: String,
    val accessCardStatusImage: String,
    val linkToSurveyDescription: String,
    val wellnessHealthInfoTitle: String,
    val gmwInterruptTitle: String,
    val gmwInterruptSubTitle: String,
    val gmwInterruptIconAccessLabel: String,
    val gmwInterruptCloseAccess: String,
    val gmwInterruptButtonTitle: String,
    val gmwInterruptButtonAccess: String,
    val title: String ="Welcome to My GMW",
    val imageAccessLabel: String,
    val iconAccessLabel: String,
    val skipButtonLabel: String,
    val skipButtonAccessLabel: String,
    val closeButtonLabel: String,
    val closeButtonAccessLabel: String,
) {
    fun getCurrentProgressText(): IntArray {
        return if (currentProgress == 0) {
            intArrayOf(maxListSize, maxListSize)
        } else {
            intArrayOf(currentProgress, maxListSize)
        }
    }
}
