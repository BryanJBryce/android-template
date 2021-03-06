@file:Suppress("unused")

package org.jdc.template.ux

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.jdc.template.inject.ViewModelFactory
import org.jdc.template.inject.ViewModelKey
import org.jdc.template.ux.about.AboutViewModel
import org.jdc.template.ux.directory.DirectoryViewModel
import org.jdc.template.ux.individual.IndividualViewModel
import org.jdc.template.ux.individualedit.IndividualEditViewModel
import org.jdc.template.ux.startup.StartupViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    
    @Binds
    @IntoMap
    @ViewModelKey(StartupViewModel::class)
    internal abstract fun bindStartupViewModel(viewModel: StartupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DirectoryViewModel::class)
    internal abstract fun bindDirectoryViewModel(viewModel: DirectoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IndividualViewModel::class)
    internal abstract fun bindIndividualViewModel(viewModel: IndividualViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IndividualEditViewModel::class)
    internal abstract fun bindIndividualEditViewModel(viewModel: IndividualEditViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    internal abstract fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel
}

