package com.sriyank.animationsdemo

import android.animation.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Animator.AnimatorListener {

    private var mRotateAnimator: ObjectAnimator? = null
    private var mScaleAnimator: ObjectAnimator? = null
    private var mTranslateAnimator: ObjectAnimator? = null
    private var mFadeAnimator: ObjectAnimator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun rotateAnimation(view: View) {

        mRotateAnimator = ObjectAnimator.ofFloat(targetImage, "rotation", 0f, 180f)
        mRotateAnimator?.apply {
            duration = 1500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()

        }
    }

    fun scaleAnimation(view: View) {

        mScaleAnimator = ObjectAnimator.ofFloat(targetImage, "scaleX", 1f, 3f)
        mScaleAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun translateAnimation(view: View) {

        mTranslateAnimator = ObjectAnimator.ofFloat(targetImage, "translationX", 0f, 200f)
        mTranslateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun fadeAnimation(view: View) {

        mFadeAnimator = ObjectAnimator.ofFloat(targetImage, "alpha", 1f, 0f)
        mFadeAnimator?.apply {
            duration = 1500
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            addListener(this@MainActivity)
            start()

        }
    }

    override fun onAnimationRepeat(animation: Animator?) {

        when (animation) {
            mRotateAnimator ->
                Log.d("@@ANIMATION@@", "Rotate Animation Repeat")

            mScaleAnimator ->

                Log.d("@@ANIMATION@@", "Scale Animation Repeat")

            mTranslateAnimator ->

                Log.d("@@ANIMATION@@", "Translate Animation Repeat")

            mFadeAnimator ->

                Log.d("@@ANIMATION@@", "Fade Animation Repeat")
        }

    }

    override fun onAnimationEnd(animation: Animator?) {
        when (animation) {
            mRotateAnimator ->
                Log.d("@@ANIMATION@@", "Rotate Animation End")

            mScaleAnimator ->
                Log.d("@@ANIMATION@@", "Scale Animation End")

            mTranslateAnimator ->
                Log.d("@@ANIMATION@@", "Translate Animation End")

            mFadeAnimator ->
                Log.d("@@ANIMATION@@", "Fade Animation End")
        }
    }

    override fun onAnimationCancel(animation: Animator?) {
        Toast.makeText(this, "Animation Cancel", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationStart(animation: Animator?) {
        when (animation) {
            mRotateAnimator ->
                Log.d("@@ANIMATION@@", "Rotate Animation Start")

            mScaleAnimator ->
                Log.d("@@ANIMATION@@", "Scale Animation Start")

            mTranslateAnimator ->
                Log.d("@@ANIMATION@@", "Translate Animation Start")

            mFadeAnimator ->
                Log.d("@@ANIMATION@@", "Fade Animation Start")
        }
    }


    fun setFromXML(view: View) {

        val animator = AnimatorInflater.loadAnimator(this, R.animator.set)
        animator.apply {
            setTarget(targetImage)
            start()
        }
    }

    fun setFromCode(view: View) {

        // Root Animator Set
        val rootSet = AnimatorSet()

        // Flip Animation
        val flip = ObjectAnimator.ofFloat(targetImage, "rotationX", 0.0f, 360.0f)
        flip.duration = 500

        // Child Animator Set
        val childSet = AnimatorSet()

        // Scale Animations
        val scaleX = ObjectAnimator.ofFloat(targetImage, "scaleX", 1.0f, 1.5f)
        scaleX?.apply {
            duration = 500
            repeatCount= 1
            repeatMode = ValueAnimator.REVERSE
            interpolator = BounceInterpolator()
        }


        val scaleY = ObjectAnimator.ofFloat(targetImage, "scaleY", 1.0f, 1.5f)
        scaleY?.apply {
            duration = 500
            repeatCount= 1
            repeatMode = ValueAnimator.REVERSE
            interpolator = BounceInterpolator()
        }


        //////Option 1 /////////
        /*rootSet.playSequentially(flip,childSet)
        childSet.playTogether(scaleX,scaleY)
        rootSet.start()*/

        //////Option 2 ////////
        rootSet.play(childSet).after(flip) //or// rootSet.play(flip).before(childSet)
        childSet.playTogether(scaleX,scaleY)
        rootSet.start()
    }


}
