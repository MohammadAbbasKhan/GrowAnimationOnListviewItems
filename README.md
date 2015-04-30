# GrowAnimationOnListviewItems
This Sample demonstrates how to animate ListView items one after the other the item grow out on initial load and squeeze in on ListView item click 
This Project contains two Modules 

1)app- Containing (Modules containing grow animation on ListView Items sample that we will be using)

2)timercontrol - Containing a simple android.os.CountDownTimer sample (Just ignore this module)

The animation uses an integer data member "systime" in ListView adapter MyAdapter that will have value 

systime = AnimationUtils.currentAnimationTimeMillis(); 

For initial load the animation used is R.anim.grow_large

initially and as the next times the getview() is called the systime value is increased by 100 milliseconds, this causes the dalay effect.

We delay the animation using method anim.setStartTime(systime);

To perform the Shrinking animation we use the "R.anim.grow_small" animation xml file and set this listener

anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (position == 0)
                            listView.setVisibility(View.INVISIBLE);

                        firstLoad = true;
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                
We can see when the animation ends we try to 

1)set the firstLoad=true which is the value by default and changes to false when any  button is clicked and then we initiate the shrinking animation

2)Fire an intent to initiate the second activity

