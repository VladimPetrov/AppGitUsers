package ru.gb.appgitusers.ui

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.gb.appgitusers.domain.IGitUserRepository

internal class GitUsersPresenterTest {

    private lateinit var presenter:GitUsersContract.Presenter

    @Mock
    private lateinit var gitUserRepository: IGitUserRepository

    @Mock
    private lateinit var view: GitUsersContract.View


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        presenter = GitUsersPresenter(gitUserRepository)
    }

    @Test
    fun attach_Test() {
        presenter.attach(view)

        verify(view, times(1)).showProgress(false)
        verify(view, times(1)).showDetails(false)
    }

    @Test
    fun onRefreshData_Test() {
        presenter.attach(view)
        presenter.onRefreshData()

        verify(view, times(1)).showProgress(true)
    }

    @Test
    fun onShowDetails_Test() {
        val pos = 1
        presenter.attach(view)
        presenter.onShowDetails(pos)

        verify(view, times(1)).showDetails(true)
    }

    @Test
    fun onCloseDetails_Test() {
        presenter.attach(view)
        presenter.onCloseDetails()

        verify(view, times(2)).showDetails(false)
    }

}