package firebasebarcelona.wallapadel.ui.di.module;

import dagger.Module;
import dagger.Provides;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.wallapadel.ui.login.presentation.GoogleLoginView;

@Module
public class ViewModule {
    private GoogleLoginView googleLoginView;
    private CourtListView courtListView;

    public ViewModule(CourtListView courtListView) {
        this.courtListView = courtListView;
    }

    public ViewModule(GoogleLoginView googleLoginView) {
        this.googleLoginView = googleLoginView;
    }

    @Provides
    GoogleLoginView provideGoogleLoginView() {
        return googleLoginView;
    }

    @Provides
    CourtListView provideCourtsListView() {
        return courtListView;
    }
}
